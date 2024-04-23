package com.coconsult.pidevspring.RestControllers.HR;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Entities.SendEmailRequest;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.Services.HR.CVStorage.FileHRInfo;

import com.coconsult.pidevspring.Services.HR.CVStorage.FilesHRController;
import com.coconsult.pidevspring.Services.HR.CVStorage.FilesStorageServiceHR;
import com.coconsult.pidevspring.Services.HR.CVStorage.ResponseMessageHR;
import com.coconsult.pidevspring.Services.HR.EmailInterviewService;
import com.coconsult.pidevspring.Services.HR.ICandidacyService;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.coconsult.pidevspring.Services.User.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/candidacy")
public class CandidacyRestController {
   // @Autowired
   @Autowired
   private EmailInterviewService emailService;
    ICandidacyService iCandidacyService;
    IJobOfferService iJobOfferService;
    FilesStorageServiceHR storageService;
    CandidacyRepository candidacyRepository;
    @PostMapping("addCandidacy")
    public Candidacy addCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addCandidate(Candidacy);
    }
    @PutMapping ("updateCandidacy")
    public Candidacy updateCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addOrUpdateCandidacy(Candidacy);
    }
    @PostMapping("addAll")
    public List<Candidacy> addAllCandidacies(@RequestBody List<Candidacy> Candidacys){
        return iCandidacyService.addAllCandidacies(Candidacys);
    }
    @GetMapping("getCandidacy/{id}")
    public Candidacy getCandidacy(@PathVariable("id") long id){
        return iCandidacyService.findCandidacyById(id);
    }
    @GetMapping("findAllCandidacies")
    public List<Candidacy> findAllCandidacies() {
        return iCandidacyService.findAllCandidacies();
    }

    @DeleteMapping("deleteCandidacy")
    public void deleteCandidacy(@RequestBody Candidacy Candidacy){
        iCandidacyService.deleteCandidacy(Candidacy);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteCandidacyById(@PathVariable("id") long id){
        iCandidacyService.deleteCandidacyById(id);
    }
    @GetMapping("/getCandidaciesByJobOfferId")
    public List<Candidacy> getCandidaciesByJobOfferId(@RequestParam Long jobOfferId) {
        return iCandidacyService.getCandidaciesByJobOfferId(jobOfferId);
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessageHR> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageHR(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageHR(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileHRInfo>> getListFiles() {
        List<FileHRInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesHRController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileHRInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/getAlCandidaciesByJobOfferId/{jobOfferId}")
    public List<Candidacy> getAllCandidaciesByJobOfferId(@PathVariable Long jobOfferId) {
        return iCandidacyService.findAllByJobOfferId(jobOfferId);
    }
    @GetMapping("/countByJobOfferId/{jobOfferId}")
    public int countCandidaciesByJobOfferId(@PathVariable Long jobOfferId) {
        return iCandidacyService.countCandidaciesByJobOfferId(jobOfferId);
    }
    @PutMapping("updateCandidacyStatus")
    public ResponseEntity<Candidacy> updateCandidacyStatus(@RequestBody Candidacy candidacy) {
        Candidacy updatedCandidacy = iCandidacyService.updateCandidacyStatus(candidacy);
        return ResponseEntity.ok(updatedCandidacy);
    }
    @GetMapping("/updateLinkedinData")
    public ResponseEntity<String> updateCandidaciesWithLinkedInData() {
        try {
            // Call the service method to update candidacies with LinkedIn data
            iCandidacyService.updateCandidaciesWithLinkedInData();
            return ResponseEntity.ok("LinkedIn data updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update LinkedIn data");
        }
    }

    @GetMapping("/linkedin")
    public ResponseEntity<String> getCandidacyInfoFromLinkedIn(@RequestParam String linkedinUrl) {
        try {
            // Call your service method to fetch information from LinkedIn using the provided URL
            String response = iCandidacyService.getCandidacyInfoFromLinkedIn(linkedinUrl);

            // Check if the response is valid
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve LinkedIn information");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve LinkedIn information due to an error");
        }
    }
    @GetMapping("/candidateStatisticsByCountry")
    public ResponseEntity<List<Object[]>> getCandidateStatisticsByCountry() {
        try {
            // Invoke the service method to get candidate statistics by country
            List<Object[]> candidateStatistics = iCandidacyService.getCandidatesByCountryStatistics();

            // Return the fetched statistics as the HTTP response
            return ResponseEntity.ok(candidateStatistics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/mostQualifiedCandidatesStatistics")
    public ResponseEntity<List<Map<String, Object>>> getMostQualifiedCandidatesStatistics() {
        try {
            // Call the service method to get statistics on the most qualified candidates
            List<Map<String, Object>> statistics = iCandidacyService.getMostQualifiedCandidatesStatistics();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/sendEmailToCandidate")
    public ResponseEntity<String> sendEmailToCandidate(@RequestBody SendEmailRequest request) {
        // Get the candidate's email from the request
        String candidateEmail = request.getEmail();

        // Check if the candidate exists in your database
        Optional<Candidacy> optionalCandidacy = candidacyRepository.findByEmail(candidateEmail);
        if (optionalCandidacy.isPresent()) {
            // Candidate exists, proceed to send the email
            try {
                String htmlContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                        "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                        "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                        "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                        "  <title>New email template 2024-04-21</title>\n" +
                        "  <!--[if (mso 16)]>\n" +
                        "    <style type=\"text/css\">\n" +
                        "    a {text-decoration: none;}\n" +
                        "    </style>\n" +
                        "    <![endif]-->\n" +
                        "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                        "  <!--[if gte mso 9]>\n" +
                        "<xml>\n" +
                        "    <o:OfficeDocumentSettings>\n" +
                        "    <o:AllowPNG></o:AllowPNG>\n" +
                        "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                        "    </o:OfficeDocumentSettings>\n" +
                        "</xml>\n" +
                        "<![endif]-->\n" +
                        "  <style type=\"text/css\">\n" +
                        "#outlook a {\n" +
                        "\tpadding:0;\n" +
                        "}\n" +
                        ".es-button {\n" +
                        "\tmso-style-priority:100!important;\n" +
                        "\ttext-decoration:none!important;\n" +
                        "}\n" +
                        "a[x-apple-data-detectors] {\n" +
                        "\tcolor:inherit!important;\n" +
                        "\ttext-decoration:none!important;\n" +
                        "\tfont-size:inherit!important;\n" +
                        "\tfont-family:inherit!important;\n" +
                        "\tfont-weight:inherit!important;\n" +
                        "\tline-height:inherit!important;\n" +
                        "}\n" +
                        ".es-desk-hidden {\n" +
                        "\tdisplay:none;\n" +
                        "\tfloat:left;\n" +
                        "\toverflow:hidden;\n" +
                        "\twidth:0;\n" +
                        "\tmax-height:0;\n" +
                        "\tline-height:0;\n" +
                        "\tmso-hide:all;\n" +
                        "}\n" +
                        "td .es-button-border:hover a.es-button-1636375556347 {\n" +
                        "\tbackground:#fe9d2d!important;\n" +
                        "\tborder-color:#fe9d2d!important;\n" +
                        "}\n" +
                        "td .es-button-border-1636375556347:hover {\n" +
                        "\tbackground:#fe9d2d!important;\n" +
                        "}\n" +
                        "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; border-left-width:0px!important; border-right-width:0px!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden table, td.es-desk-hidden table { display:table!important } table.es-desk-hidden .es-hidden, td.es-desk-hidden .es-hidden { display:none!important } table.es-desk-hidden td.es-hidden, td.es-desk-hidden td.es-hidden { display:none!important } table.es-desk-hidden td.es-visible, td.es-desk-hidden td.es-visible { display:table-cell!important } table.es-desk-hidden td.es-vertical-align-top, td.es-desk-hidden td.es-vertical-align-top { vertical-align:top!important } table.es-desk-hidden td.es-vertical-align-middle, td.es-desk-hidden td.es-vertical-align-middle { vertical-align:middle!important } table.es-desk-hidden td.es-vertical-align-bottom, td.es-desk-hidden td.es-vertical-align-bottom { vertical-align:bottom!important } table.es-desk-hidden td.es-m-p0, td.es-desk-hidden td.es-m-p0r, td.es-desk-hidden td.es-m-p0l, td.es-desk-hidden td.es-m-p0t, td.es-desk-hidden td.es-m-p0b, td.es-desk-hidden td.es-m-p20b { padding:0!important } table.es-desk-hidden td.es-m-p10b, td.es-desk-hidden td.es-m-p15b, td.es-desk-hidden td.es-m-p30b, td.es-desk-hidden td.es-m-p40b, td.es-desk-hidden td.es-m-p25t, td.es-desk-hidden td.es-m-p40t, td.es-desk-hidden td.es-m-p35t { padding-bottom:0!important } table.es-desk-hidden td.es-m-p5t, td.es-desk-hidden td.es-m-p5b, td.es-desk-hidden td.es-m-p5r, td.es-desk-hidden td.es-m-p5l, td.es-desk-hidden td.es-m-p10r, td.es-desk-hidden td.es-m-p10l, td.es-desk-hidden td.es-m-p15r, td.es-desk-hidden td.es-m-p15l, td.es-desk-hidden td.es-m-p20r, td.es-desk-hidden td.es-m-p20l, td.es-desk-hidden td.es-m-p25r, td.es-desk-hidden td.es-m-p25l, td.es-desk-hidden td.es-m-p30r, td.es-desk-hidden td.es-m-p30l { padding-right:0!important; padding-left:0!important } table.es-desk-hidden td.es-m-p5, td.es-desk-hidden td.es-m-pv5, td.es-desk-hidden td.es-m-ph5 { padding:0 5px!important } table.es-desk-hidden td.es-m-p5t { padding-top:0!important } table.es-desk-hidden td.es-m-p5r, td.es-desk-hidden td.es-m-p5v { padding-right:0!important } table.es-desk-hidden td.es-m-p5b, td.es-desk-hidden td.es-m-p5v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p5l, td.es-desk-hidden td.es-m-p5h { padding-left:0!important } table.es-desk-hidden td.es-m-p10t { padding-top:0!important } table.es-desk-hidden td.es-m-p10r, td.es-desk-hidden td.es-m-p10v { padding-right:0!important } table.es-desk-hidden td.es-m-p10b, td.es-desk-hidden td.es-m-p10v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p10l, td.es-desk-hidden td.es-m-p10h { padding-left:0!important } table.es-desk-hidden td.es-m-p15t { padding-top:0!important } table.es-desk-hidden td.es-m-p15r, td.es-desk-hidden td.es-m-p15v { padding-right:0!important } table.es-desk-hidden td.es-m-p15b, td.es-desk-hidden td.es-m-p15v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p15l, td.es-desk-hidden td.es-m-p15h { padding-left:0!important } table.es-desk-hidden td.es-m-p20t { padding-top:0!important } table.es-desk-hidden td.es-m-p20r, td.es-desk-hidden td.es-m-p20v { padding-right:0!important } table.es-desk-hidden td.es-m-p20b, td.es-desk-hidden td.es-m-p20v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p20l, td.es-desk-hidden td.es-m-p20h { padding-left:0!important } table.es-desk-hidden td.es-m-p25t { padding-top:0!important } table.es-desk-hidden td.es-m-p25r, td.es-desk-hidden td.es-m-p25v { padding-right:0!important } table.es-desk-hidden td.es-m-p25b, td.es-desk-hidden td.es-m-p25v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p25l, td.es-desk-hidden td.es-m-p25h { padding-left:0!important } table.es-desk-hidden td.es-m-p30t { padding-top:0!important } table.es-desk-hidden td.es-m-p30r, td.es-desk-hidden td.es-m-p30v { padding-right:0!important } table.es-desk-hidden td.es-m-p30b, td.es-desk-hidden td.es-m-p30v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p30l, td.es-desk-hidden td.es-m-p30h { padding-left:0!important } table.es-desk-hidden td.es-m-p35t { padding-top:0!important } table.es-desk-hidden td.es-m-p35r, td.es-desk-hidden td.es-m-p35v { padding-right:0!important } table.es-desk-hidden td.es-m-p35b, td.es-desk-hidden td.es-m-p35v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p35l, td.es-desk-hidden td.es-m-p35h { padding-left:0!important } table.es-desk-hidden td.es-m-p40t { padding-top:0!important } table.es-desk-hidden td.es-m-p40r, td.es-desk-hidden td.es-m-p40v { padding-right:0!important } table.es-desk-hidden td.es-m-p40b, td.es-desk-hidden td.es-m-p40v { padding-bottom:0!important } table.es-desk-hidden td.es-m-p40l, td.es-desk-hidden td.es-m-p40h { padding-left:0!important } table.es-header-desk, table.es-content-desk, table.es-footer-desk { width:600px!important }\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body style=\"width:100%;font-family:'Open Sans',sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                        "  <div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\">\n" +
                        "    <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                        "      <tr style=\"border-collapse:collapse\">\n" +
                        "        <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                        "          <!--[if mso]>\n" +
                        "<table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "<tr>\n" +
                        "<td style=\"width:270px\" valign=\"top\">\n" +
                        "<![endif]-->\n" +
                        "          <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td class=\"es-m-p0r es-m-p20b esd-container-frame\" align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                        "                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                        "                  <tr style=\"border-collapse:collapse\">\n" +
                        "                    <td align=\"center\" style=\"padding:0;Margin:0\"><img src=\"https://cdn-images.mailchimp.com/template_images/gallery/1fb99cd1-09c1-4b29-99cb-c46e63c71b32.jpg\" alt=\"\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"270\"></td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </td>\n" +
                        "            </tr>\n" +
                        "          </table>\n" +
                        "          <!--[if mso]>\n" +
                        "</td>\n" +
                        "<td style=\"width:20px\"></td>\n" +
                        "<td style=\"width:270px\" valign=\"top\">\n" +
                        "<![endif]-->\n" +
                        "          <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"center\" style=\"padding:0;Margin:0;width:270px\">\n" +
                        "                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                        "                  <tr style=\"border-collapse:collapse\">\n" +
                        "                    <td align=\"center\" style=\"padding:0;Margin:0;padding-top:5px\"><h2 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:'Open Sans',sans-serif;font-size:22px;font-style:normal;font-weight:bold;color:#333333\">April's Favorite Reads</h2></td>\n" +
                        "                  </tr>\n" +
                        "                  <tr style=\"border-collapse:collapse\">\n" +
                        "                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">As we enter into spring, I've curated a list of my top picks for you. Whether you're in the mood for light-hearted romance or an intense thriller, there's something for everyone.</p></td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </td>\n" +
                        "            </tr>\n" +
                        "          </table>\n" +
                        "          <!--[if mso]>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "<![endif]-->\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "    <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#f6f6f6\" align=\"center\">\n" +
                        "      <tr style=\"border-collapse:collapse\">\n" +
                        "        <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-left:20px;padding-right:20px\">\n" +
                        "          <table class=\"es-content esd-block-text es-m-txt-c\" cellspacing=\"0\" cellpadding=\"0\" width=\"560\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent\">\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"center\" style=\"padding:0;Margin:0\"><h2 style=\"Margin:0;line-height:30px;mso-line-height-rule:exactly;font-family:'Open Sans',sans-serif;font-size:25px;font-style:normal;font-weight:bold;color:#333333\">1. Romance</h2></td>\n" +
                        "            </tr>\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">Looking for a feel-good romance to warm your heart? Try 'Love in the Afternoon' by Lisa Kleypas. With its witty dialogue and charming characters, it's the perfect escape from reality.</p></td>\n" +
                        "            </tr>\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">Find it <a target=\"_blank\" href=\"https://www.example.com\" style=\"color:#0B5394;text-decoration:underline\">here</a>.</p></td>\n" +
                        "            </tr>\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"center\" style=\"padding:0;Margin:0\"><h2 style=\"Margin:0;line-height:30px;mso-line-height-rule:exactly;font-family:'Open Sans',sans-serif;font-size:25px;font-style:normal;font-weight:bold;color:#333333\">2. Mystery & Thriller</h2></td>\n" +
                        "            </tr>\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">'The Silent Patient' by Alex Michaelides is a gripping psychological thriller that will keep you guessing until the very end. It's a must-read for fans of the genre.</p></td>\n" +
                        "            </tr>\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">Find it <a target=\"_blank\" href=\"https://www.example.com\" style=\"color:#0B5394;text-decoration:underline\">here</a>.</p></td>\n" +
                        "            </tr>\n" +
                        "          </table>\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "    <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#f6f6f6\" align=\"center\">\n" +
                        "      <tr style=\"border-collapse:collapse\">\n" +
                        "        <td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                        "          <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;\">\n" +
                        "            <tr style=\"border-collapse:collapse\">\n" +
                        "              <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                        "                <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                        "                  <tr style=\"border-collapse:collapse\">\n" +
                        "                    <td align=\"left\" style=\"padding:20px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'Open Sans',sans-serif;line-height:24px;color:#666666\">If you have any questions or need further assistance, feel free to reach out to us at support@example.com.</p></td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </td>\n" +
                        "            </tr>\n" +
                        "          </table>\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "  </div>\n" +
                        "</body>\n" +
                        "</html>";
                // Send the email to the candidate
                emailService.send(candidateEmail, htmlContent);

                return ResponseEntity.ok("Email sent successfully to candidate's email: " + candidateEmail);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send email to candidate: " + candidateEmail);
            }
        } else {
            // Candidate not found in the database
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Candidate not found with email: " + candidateEmail);
        }
    }

    @GetMapping("/updateVerifEmail")
    public ResponseEntity<String> updateVerifEmailForAllCandidacies() {
        try {
            // Call the service method to update verifEmail for all candidacies
            iCandidacyService.updateVerifEmailForAllCandidacies();
            return ResponseEntity.ok("verifEmail updated successfully for all candidacies");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update verifEmail for all candidacies");
        }
    }


}

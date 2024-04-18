package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.Services.HR.CVStorage.FileHRInfo;
//import com.coconsult.pidevspring.Services.HR.CVStorage.FilesHRController;
import com.coconsult.pidevspring.Services.HR.CVStorage.FilesHRController;
import com.coconsult.pidevspring.Services.HR.CVStorage.FilesStorageServiceHR;
import com.coconsult.pidevspring.Services.HR.CVStorage.ResponseMessageHR;
import com.coconsult.pidevspring.Services.HR.ICandidacyService;
import com.coconsult.pidevspring.Services.HR.IJobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/candidacy")
public class CandidacyRestController {
   // @Autowired
    ICandidacyService iCandidacyService;
    IJobOfferService iJobOfferService;
    FilesStorageServiceHR storageService;
    @PostMapping("addCandidacy")
    public Candidacy addCandidacy (@RequestBody Candidacy Candidacy){
        return iCandidacyService.addOrUpdateCandidacy(Candidacy);
    }

//    @PostMapping("/addCandidacy/{jobOffer_id}")
//    public Candidacy addCandidacy(@RequestBody Candidacy candidacy, @PathVariable("jobOffer_id") long jobOffer_id) {
//        JobOffer job = iJobOfferService.findOneJobOffer(jobOffer_id);
//        candidacy.setJob_offer(job);
//        return iCandidacyService.addCandidate(candidacy);
//    }

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


}

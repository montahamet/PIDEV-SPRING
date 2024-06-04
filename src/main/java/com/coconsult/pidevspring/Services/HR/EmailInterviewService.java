package com.coconsult.pidevspring.Services.HR;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service("InterviewServiceEmailService")

public class EmailInterviewService {
    @Autowired
    private final JavaMailSender mailSender;

    @Autowired
    public EmailInterviewService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Async
    public void send(String to, String emailContent) {
        // Using the provided HTML email template
        String htmlContent = "<DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                " <head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "  <title>New Template</title><!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100%!important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]--><!--[if!mso]><!-- -->\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "/* Le reste du code CSS */\n" +
                "  </style>\n" +
                " </head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "  <div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"en\" style=\"background-color:#F6F6F6\"><!--[if gte mso 9]>\n" +
                "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "				<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\n" +
                "			</v:background>\n" +
                "		<![endif]-->\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6\">\n" +
                "     <tr>\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed!important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "         <tr>\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;border-radius:20px 20px 0 0;width:600px\" role=\"none\">\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\"><!--[if mso]><table style=\"width:520px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:156px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr>\n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:156px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/coconsultlogo.png\" alt=\"Logo\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;font-size:12px\" width=\"100\" title=\"Logo\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:344px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-right\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:344px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td style=\"padding:0;Margin:0\">\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr class=\"links\">\n" +
                "                          <td align=\"center\" valign=\"top\" width=\"100%\" id=\"esd-menu-id-0\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:15px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"http://localhost:4200/JobOffer/findAllJobOffersfront\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:12px\">Home</a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed!important;width:100%\">\n" +
                "         <tr>\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;width:600px\">\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/frame_367_QCr.png\" style=\"Margin:0;padding-top:30px;padding-bottom:30px;padding-left:40px;padding-right:40px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/frame_367_QCr.png);background-repeat:no-repeat;background-position:center bottom\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px\" role=\"presentation\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" style=\"padding:20px;Margin:0\"><h1 style=\"Margin:0;line-height:60px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:50px;font-style:normal;font-weight:normal;color:#FFFFFF\">Interview Meeting Invitation<strong></strong></h1></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Hello Candidate,</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">We are delighted to inform you that you have successfully moved to the next stage of our hiring process. The next step involves attending an interview meeting to assess your skills and qualifications further.</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Please find the link to the meet attached to this email. If you encounter any technical difficulties or have any questions, feel free to reach out to me.</p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr>\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;width:600px\">\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:10px;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/image_20240426_102729291removebgpreview.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"500\"></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"es-m-p15t\" style=\"padding:0;Margin:0;padding-top:5px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">&nbsp;Application&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Quiz&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Interview&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Result</h3></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr>\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-left:40px;padding-right:40px\"><!--[if mso]><table style=\"width:520px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:111px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:91px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px 30px 0px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/rectangle_5597_bEP.png);background-repeat:no-repeat;background-position:right bottom\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/rectangle_5597_bEP.png\" role=\"presentation\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"es-m-p5t es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px\"><h2 style=\"Margin:0;line-height:34px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:28px;font-style:normal;font-weight:normal;color:#FFFFFF\">23</h2></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"45%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:45% !important;display:inline-table\" role=\"presentation\">\n" +
                "                         <tr>\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Feb</h3></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                  <td class=\"es-hidden\" style=\"padding:0;Margin:0;width:20px\"></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:236px\" valign=\"top\"><![endif]-->\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:236px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"es-m-p15t\" style=\"padding:0;Margin:0;padding-top:5px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Interview Session</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"es-m-p5t\" style=\"padding:0;Margin:0;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Wednesday • <a href=\"https://viewstripo.email\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#FFFFFF;font-size:16px\">11AM PT</a>&nbsp;|&nbsp;<a href=\"https://viewstripo.email\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#FFFFFF;font-size:16px\">2PM ET</a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:153px\" valign=\"top\"><![endif]-->\n"+
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:153px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"right\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:30px\"><!--[if mso]><a href=\"https://meet.jit.si/bwb-bfqi-vmh\" target=\"_blank\" hidden>\n" +
                "	<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://meet.jit.si/bwb-bfqi-vmh\" \n" +
                "                style=\"height:39px; v-text-anchor:middle; width:135px\" arcsize=\"50%\" stroke=\"f\"  fillcolor=\"#ffffff\">\n" +
                "		<w:anchorlock></w:anchorlock>\n" +
                "		<center style='color:#e69138; font-family:Poppins, sans-serif; font-size:14px; font-weight:400; line-height:14px;  mso-text-raise:1px'>Join Meet</center>\n" +
                "	</v:roundrect></a>\n" +
                "<![endif]--><!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#e69138;background:#ffffff;border-width:0px;display:inline-block;border-radius:30px;width:auto;mso-hide:all\"><a href=\"https://meet.jit.si/bwb-bfqi-vmh\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#e69138;font-size:16px;padding:10px 20px 10px 20px;display:inline-block;background:#ffffff;border-radius:30px;font-family:Poppins, sans-serif;font-weight:normal;font-style:normal;line-height:19px;width:auto;text-align:center;mso-padding-alt:0;mso-border-alt:10px solid #ffffff\">Join Meet</a></span><!--<![endif]--></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]-->\n"+
                "<tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr>\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr>\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\" bgcolor=\"#ffffff\" role=\"none\">\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#3d85c6\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px;background-color:#3d85c6\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Here to help,</p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "<tr>\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#3d85c6\" style=\"padding:0;Margin:0;padding-top:30px;padding-left:40px;padding-right:40px;background-color:#3d85c6;border-radius:0px 0px 0px 20px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_32d55458cc42a293bf7e1691e2c3cf9c/images/frame_367_bYc.png);background-repeat:no-repeat;background-position:center bottom\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_32d55458cc42a293bf7e1691e2c3cf9c/images/frame_367_bYc.png\"><!--[if mso]><table dir=\"ltr\" cellpadding=\"0\" cellspacing=\"0\"><tr><td><table dir=\"rtl\" style=\"width:520px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td dir=\"ltr\" style=\"width:340px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" class=\"es-m-p20b\" style=\"padding:0;Margin:0;width:340px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:20px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Montaha Metjaouel |&nbsp;HR</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td style=\"padding:0;Margin:0\">\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr class=\"links-images-left\">\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"tel:+134578990\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/95711614763048218.png\" alt=\"+1 345 789 90\" title=\"+1 345 789 90\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">+1 345 789 90</a></td>\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"tel:+134578990\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/69541614947093393.png\" alt=\"+1 345 789 90\" title=\"+1 345 789 90\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">+1 345 789 90</a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td style=\"padding:0;Margin:0\">\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr class=\"links-images-left\">\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;border:0\"><a target=\"_blank\" href=\"mailto:montaha.metjaouel@esprit.tn\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/58641614773761370.png\" alt=\"Montaha Metjaouel\" title=\"Montaha Metjaouel\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">Montaha Metjaouel</a></td>\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;border:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/60191614948456055.png\" alt=\"123 Lorem ipsum\" title=\"123 Lorem ipsum\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">123 Lorem ipsum</a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "<tr>\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;font-size:0\">\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://www.facebook.com/montaha.metjaouel/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"Facebook\" src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-white/facebook-logo-white.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://www.linkedin.com/in/montaha-metjaouel-a59636274/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"LinkedIn\" src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-white/linkedin-logo-white.png\" alt=\"In\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://github.com/montahamet\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"GitHub\" src=\"https://fhociov.stripocdn.email/content/assets/img/other-icons/logo-white/github-logo-white.png\" alt=\"GitHub\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"24816800\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"Whatsapp\" src=\"https://fhociov.stripocdn.email/content/assets/img/messenger-icons/logo-white/whatsapp-logo-white.png\" alt=\"Whatsapp\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n"+
                "    </table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:160px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px 30px 0 0\" role=\"presentation\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"es-m-p10l\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/whatsapp_image_20240426_at_91845_amremovebgpreview_60B.png\" alt=\"Montaha Metjaouel\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;font-size:12px\" width=\"160\" title=\"Montaha Metjaouel\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "         <tr>\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3d85c6;width:600px\" bgcolor=\"#3d85c6\" role=\"none\">\n" +
                "             <tr>\n" +
                "              <td class=\"es-m-p30t es-m-p30b es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#ffffff\" style=\"padding:40px;Margin:0;background-color:#ffffff;border-radius:0px 20px 0px 0px\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr>\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:30px\"><a target=\"_blank\" href=\"https://www.facebook.com/coconsultesn/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:14px\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-gray/facebook-logo-gray.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:30px\"><a target=\"_blank\" href=\"https://www.coconsult.fr/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:14px\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-gray/linkedin-logo-gray.png\" alt=\"In\" title=\"LinkedIn\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/messenger-icons/logo-gray/whatsapp-logo-gray.png\" alt=\"Whatsapp\" title=\"Whatsapp\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td dir=\"ltr\" style=\"width:20px\"></td><td dir=\"ltr\" style=\"width:160px\" valign=\"top\"><![endif]-->\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "<tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;font-size:0\">\n" +
                "                       <table border=\"0\" width=\"45%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr>\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:unset;height:1px;width:100%;margin:0px\"></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\">Coconsult •&nbsp;<a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#999999;font-size:14px\" href=\"https://viewstripo.email\">2</a>024 • <a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#999999;font-size:14px\" href=\"\">All rights reserved</a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table></td>\n" +
                "     </tr>\n" +
                "   </table>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject("Interview");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

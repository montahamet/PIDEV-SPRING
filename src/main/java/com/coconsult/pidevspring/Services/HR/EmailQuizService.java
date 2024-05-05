package com.coconsult.pidevspring.Services.HR;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("QuizServiceEmailService")
public class EmailQuizService {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    public EmailQuizService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Async
    public void send(String to, String emailContent) {
        // Using the provided HTML email template
        String htmlQuiz ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" +
                "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\r\n" +
                "<head>\r\n" +
                "  <meta charset=\"UTF-8\">\r\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\r\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\r\n" +
                "  <title>New Template</title><!--[if (mso 16)]>\r\n" +
                "    <style type=\"text/css\">\r\n" +
                "    a {text-decoration: none;}\r\n" +
                "    </style>\r\n" +
                "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\r\n" +
                "<xml>\r\n" +
                "    <o:OfficeDocumentSettings>\r\n" +
                "    <o:AllowPNG></o:AllowPNG>\r\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n" +
                "    </o:OfficeDocumentSettings>\r\n" +
                "</xml>\r\n" +
                "<![endif]--><!--[if !mso]><!-- -->\r\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"><!--<![endif]-->\r\n" +
                "  <style type=\"text/css\">\r\n" +
                "#outlook a {\r\n" +
                "	padding:0;\r\n" +
                "}\r\n" +
                ".es-button {\r\n" +
                "	mso-style-priority:100!important;\r\n" +
                "	text-decoration:none!important;\r\n" +
                "}\r\n" +
                "a[x-apple-data-detectors] {\r\n" +
                "	color:inherit!important;\r\n" +
                "	text-decoration:none!important;\r\n" +
                "	font-size:inherit!important;\r\n" +
                "	font-family:inherit!important;\r\n" +
                "	font-weight:inherit!important;\r\n" +
                "	line-height:inherit!important;\r\n" +
                "}\r\n" +
                ".es-desk-hidden {\r\n" +
                "	display:none;\r\n" +
                "	float:left;\r\n" +
                "	overflow:hidden;\r\n" +
                "	width:0;\r\n" +
                "	max-height:0;\r\n" +
                "	line-height:0;\r\n" +
                "	mso-hide:all;\r\n" +
                "}\r\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:left } h2 { font-size:24px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }\r\n" +
                "@media screen and (max-width:384px) {.mail-message-content { width:414px!important } }\r\n" +
                "</style>\r\n" +
                "</head>\n" +
                "<body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\r\n" +
                "  <div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"en\" style=\"background-color:#F6F6F6\"><!--[if gte mso 9]>\r\n" +
                "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n" +
                "				<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\r\n" +
                "			</v:background>\r\n" +
                "		<![endif]-->\r\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6\">\r\n" +
                "     <tr>\r\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\r\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n" +
                "         <tr>\r\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;border-radius:20px 20px 0 0;width:600px\" role=\"none\">\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\"><!--[if mso]><table style=\"width:520px\" cellpadding=\"0\"\r\n" +
                "                            cellspacing=\"0\"><tr><td style=\"width:156px\" valign=\"top\"><![endif]-->\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:156px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/coconsultlogo.png\" alt=\"Logo\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;font-size:12px\" width=\"100\" title=\"Logo\"></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:344px\" valign=\"top\"><![endif]-->\r\n" +
                "<table class=\"es-right\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:344px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td style=\"padding:0;Margin:0\">\r\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr class=\"links\">\r\n" +
                "                          <td align=\"center\" valign=\"top\" width=\"100%\" id=\"esd-menu-id-0\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:15px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"http://localhost:4200/JobOffer/findAllJobOffersfront\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:12px\">Home</a></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\r\n" +
                "             </tr>\r\n" +
                "           </table></td>\r\n" +
                "         </tr>\r\n" +
                "       </table>\r\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n" +
                "         <tr>\r\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;width:600px\">\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/frame_367_QCr.png\" style=\"Margin:0;padding-top:30px;padding-bottom:30px;padding-left:40px;padding-right:40px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/frame_367_QCr.png);background-repeat:no-repeat;background-position:center bottom\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px\" role=\"presentation\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" style=\"padding:20px;Margin:0\"><h1 style=\"Margin:0;line-height:60px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:50px;font-style:normal;font-weight:normal;color:#FFFFFF\">You're invited to take this Quiz</h1></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Hello Candidate,</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">We are delighted to inform you that you have successfully moved to the next stage of our hiring process. The next step involves completing a quiz designed to assess your skills and qualifications further.<br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Please find the link to the quiz attached to this email. If you encounter any technical difficulties or have any questions, feel free to reach out to me.</p></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "           </table></td>\r\n" +
                "         </tr>\r\n" +
                "       </table>\r\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n" +
                "         <tr>\r\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D85C6;width:600px\">\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:10px;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/image_20240426_102658156removebgpreview.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"500\"></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" class=\"es-m-p15t\" style=\"padding:0;Margin:0;padding-top:5px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">&nbsp;Application&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Quiz&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Interview&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;Result</h3></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\r\n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "<tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-left:40px;padding-right:40px\"><!--[if mso]><table style=\"width:520px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:111px\" valign=\"top\"><![endif]-->\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:91px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px 30px 0px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/rectangle_5597_bEP.png);background-repeat:no-repeat;background-position:right bottom\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_1298dc8aa01fe34f7c62e4093dd0ee11c9d95a479ff5bbf11dd3d0b4ae8fa7d2/images/rectangle_5597_bEP.png\" role=\"presentation\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" class=\"es-m-p5t es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px\"><h2 style=\"Margin:0;line-height:34px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:28px;font-style:normal;font-weight:normal;color:#FFFFFF\">23</h2></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\r\n" +
                "                       <table border=\"0\" width=\"45%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:45% !important;display:inline-table\" role=\"presentation\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:10px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Feb</h3></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                  <td class=\"es-hidden\" style=\"padding:0;Margin:0;width:20px\"></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td><td style=\"width:236px\" valign=\"top\"><![endif]-->\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:236px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" class=\"es-m-p15t\" style=\"padding:0;Margin:0;padding-top:5px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Q&amp;A Session</h3></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" class=\"es-m-p5t\" style=\"padding:0;Margin:0;padding-top:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Wednesday • <a href=\"https://viewstripo.email\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#FFFFFF;font-size:16px\">11AM PT</a>&nbsp;|&nbsp;<a href=\"https://viewstripo.email\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#FFFFFF;font-size:16px\">2PM ET</a></p></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:153px\" valign=\"top\"><![endif]-->\r\n" +
                "               </tr>\r\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:153px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"right\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:30px\"><!--[if mso]><a href=\"http://localhost:4200/quiz\" target=\"_blank\" hidden>\r\n" +
                "	<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"http://localhost:4200/quiz\" \r\n" +
                "                style=\"height:39px; v-text-anchor:middle; width:147px\" arcsize=\"50%\" stroke=\"f\"  fillcolor=\"#ffffff\">\r\n" +
                "		<w:anchorlock></w:anchorlock>\r\n" +
                "		<center style='color:#e69138; font-family:Poppins, sans-serif; font-size:14px; font-weight:400; line-height:14px;  mso-text-raise:1px'>Start the Quiz</center>\r\n" +
                "	</v:roundrect></a>\r\n" +
                "<![endif]--><!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#e69138;background:#ffffff;border-width:0px;display:inline-block;border-radius:30px;width:auto;mso-hide:all\"><a href=\"http://localhost:4200/quiz\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#e69138;font-size:16px;padding:10px 20px 10px 20px;display:inline-block;background:#ffffff;border-radius:30px;font-family:Poppins, sans-serif;font-weight:normal;font-style:normal;line-height:19px;width:auto;text-align:center;mso-padding-alt:0;mso-border-alt:10px solid #ffffff\">Start the Quiz</a></span><!--<![endif]--></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\r\n" +
                "             </tr>\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\r\n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #9fc5e8;background:unset;height:1px;width:100%;margin:0px\"></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "           </table></td>\r\n" +
                "         </tr>\r\n" +
                "       </table>\r\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n" +
                "         <tr>\r\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" +
                "           <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\" bgcolor=\"#ffffff\" role=\"none\">\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#3d85c6\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px;background-color:#3d85c6\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:24px;color:#FFFFFF;font-size:16px\">Here to help,</p></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#3d85c6\" style=\"padding:0;Margin:0;padding-top:30px;padding-left:40px;padding-right:40px;background-color:#3d85c6;border-radius:0px 0px 0px 20px;background-image:url(https://fhociov.stripocdn.email/content/guids/CABINET_32d55458cc42a293bf7e1691e2c3cf9c/images/frame_367_bYc.png);background-repeat:no-repeat;background-position:center bottom\" background=\"https://fhociov.stripocdn.email/content/guids/CABINET_32d55458cc42a293bf7e1691e2c3cf9c/images/frame_367_bYc.png\"><!--[if mso]><table dir=\"ltr\" cellpadding=\"0\" cellspacing=\"0\"><tr><td><table dir=\"rtl\" style=\"width:520px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td dir=\"ltr\" style=\"width:340px\" valign=\"top\"><![endif]-->\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" class=\"es-m-p20b\" style=\"padding:0;Margin:0;width:340px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:20px\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#FFFFFF\">Montaha Metjaouel |&nbsp;HR</h3></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td style=\"padding:0;Margin:0\">\r\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr class=\"links-images-left\">\r\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"tel:+134578990\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/95711614763048218.png\" alt=\"+1 345 789 90\" title=\"+1 345 789 90\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">+1 345 789 90</a></td>\r\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"tel:+134578990\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/69541614947093393.png\" alt=\"+1 345 789 90\" title=\"+1 345 789 90\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">+1 345 789 90</a></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "<tr>\r\n" +
                "                      <td style=\"padding:0;Margin:0\">\r\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr class=\"links-images-left\">\r\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;border:0\"><a target=\"_blank\" href=\"mailto:montaha.metjaouel@esprit.tn\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/58641614773761370.png\" alt=\"Montaha Metjaouel\" title=\"Montaha Metjaouel\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">Montaha Metjaouel</a></td>\r\n" +
                "                          <td align=\"left\" valign=\"top\" width=\"50%\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;border:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#FFFFFF;font-size:16px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_3b670d78779801705eef224a1b9fbd70/images/60191614948456055.png\" alt=\"123 Lorem ipsum\" title=\"123 Lorem ipsum\" align=\"absmiddle\" width=\"20\" style=\"display:inline-block !important;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;padding-right:5px;vertical-align:middle\">123 Lorem ipsum</a></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;font-size:0\">\r\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://www.facebook.com/montaha.metjaouel/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"Facebook\" src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-white/facebook-logo-white.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://www.linkedin.com/in/montaha-metjaouel-a59636274/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"LinkedIn\" src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-white/linkedin-logo-white.png\" alt=\"In\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:20px\"><a target=\"_blank\" href=\"https://github.com/montahamet\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"GitHub\" src=\"https://fhociov.stripocdn.email/content/assets/img/other-icons/logo-white/github-logo-white.png\" alt=\"GitHub\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"24816800\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#FFFFFF;font-size:16px\"><img title=\"Whatsapp\" src=\"https://fhociov.stripocdn.email/content/assets/img/messenger-icons/logo-white/whatsapp-logo-white.png\" alt=\"Whatsapp\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td><td dir=\"ltr\" style=\"width:20px\"></td><td dir=\"ltr\" style=\"width:160px\" valign=\"top\"><![endif]-->\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:160px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#0b5394\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#0b5394;border-radius:30px 30px 0 0\" role=\"presentation\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" class=\"es-m-p10l\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://fhociov.stripocdn.email/content/guids/CABINET_268fc5d79e7f8ffc29da8ef45dd20cc209fa9ed46a03f8c6107990bbbb042f04/images/whatsapp_image_20240426_at_91845_amremovebgpreview_60B.png\" alt=\"Montaha Metjaouel\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;font-size:12px\" width=\"160\" title=\"Montaha Metjaouel\"></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table><!--[if mso]></td></tr></table></td></tr></table><![endif]--></td>\r\n" +
                "             </tr>\r\n" +
                "           </table></td>\r\n" +
                "         </tr>\r\n" +
                "       </table>\r\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n" +
                "         <tr>\r\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" +
                "           <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3d85c6;width:600px\" bgcolor=\"#3d85c6\" role=\"none\">\r\n" +
                "             <tr>\r\n" +
                "              <td class=\"es-m-p30t es-m-p30b es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#ffffff\" style=\"padding:40px;Margin:0;background-color:#ffffff;border-radius:0px 20px 0px 0px\">\r\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                 <tr>\r\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:520px\">\r\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\r\n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:30px\"><a target=\"_blank\" href=\"https://www.facebook.com/coconsultesn/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:14px\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-gray/facebook-logo-gray.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:30px\"><a target=\"_blank\" href=\"https://www.coconsult.fr/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:14px\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/social-icons/logo-gray/linkedin-logo-gray.png\" alt=\"In\" title=\"LinkedIn\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\r\n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img src=\"https://fhociov.stripocdn.email/content/assets/img/messenger-icons/logo-gray/whatsapp-logo-gray.png\" alt=\"Whatsapp\" title=\"Whatsapp\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;font-size:0\">\r\n" +
                "                       <table border=\"0\" width=\"45%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" +
                "                         <tr>\r\n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:unset;height:1px;width:100%;margin:0px\"></td>\r\n" +
                "                         </tr>\r\n" +
                "                       </table></td>\r\n" +
                "                     </tr>\r\n" +
                "                     <tr>\r\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\">Coconsult •&nbsp;<a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#999999;font-size:14px\" href=\"https://viewstripo.email\">2</a>024 • <a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#999999;font-size:14px\" href=\"\">All rights reserved</a></p></td>\r\n" +
                "                     </tr>\r\n" +
                "                   </table></td>\r\n" +
                "                 </tr>\r\n" +
                "               </table></td>\r\n" +
                "             </tr>\r\n" +
                "           </table></td>\r\n" +
                "         </tr>\r\n" +
                "       </table></td>\r\n" +
                "     </tr>\r\n" +
                "   </table>\r\n" +
                "  </div>\r\n" +
                " </body>\r\n" +
                "</html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject("Quiz");
            helper.setText(htmlQuiz, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

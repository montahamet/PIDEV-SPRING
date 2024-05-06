package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.EmailDetails;
import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
public class TaskSchedulerService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    private Set<Long> processedTasks = new HashSet<>();

    @Scheduled(cron = "0 0 * * * *")//  @Scheduled(cron = "0 * * * * ?") chaque minute
    public void checkTasksForLateAndSendEmail() {
        // Récupérer toutes les tâches
        Iterable<Task> allTasks = taskService.getAllTasks();

        // Obtenir la date actuelle
        long currentTime = System.currentTimeMillis();

        // Parcourir les tâches
        for (Task task : allTasks) {
            // Vérifier si la tâche est en retard et n'a pas déjà été traitée
            if (taskIsLate(task, currentTime) && !processedTasks.contains(task.getTaskid())) {
                // Envoyer l'e-mail
                try {
                    sendEmailToProjectManager(task);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                // Ajouter la tâche à la liste des tâches traitées
                processedTasks.add(task.getTaskid());
            }
        }
    }

    private boolean taskIsLate(Task task, long currentTime) {
        // Récupérer la date d'échéance de la tâche
        LocalDate dueDate = task.getDueDateTask();
        LocalDateTime dueDateTime = dueDate.atStartOfDay(); // Convertir en LocalDateTime à minuit de la date donnée
        // Convertir en timestamp en millisecondes pour comparaison
        long dueDateTimeMillis = dueDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // Vérifier si la date d'échéance est passée
        return dueDateTimeMillis <= currentTime && (task.getTaskStatus().equals(StatusTask.TODO) || task.getTaskStatus().equals(StatusTask.INPROGRESS));
    }

    private void sendEmailToProjectManager(Task task) throws MessagingException {
        // Créer le contenu de l'e-mail
        String subject = "Task overdue";
        String htmlBody = createHtmlEmailBody(task);
        // Envoyer l'e-mail
        emailService.sendHtmlEmail("malek.frikhi@esprit.tn", subject, htmlBody);
    }
    private String createHtmlEmailBody(Task task) {
        // Créez votre contenu HTML en utilisant les données de la tâche
        // Exemple:
        String taskName = task.getTaskname();
        String employeeName = task.getEmployeeTask().getFirstname();
        String htmlBody = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "  <title></title>\n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "  <!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "<style>\n" +
                "/* CSS personnalisé */\n" +
                "body {\n" +
                "  font-family: Arial, sans-serif;\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "  background-color: #f0f0f0;\n" +
                "}\n" +
                ".wrapper {\n" +
                "  width: 100%;\n" +
                "  max-width: 600px;\n" +
                "  margin: 0 auto;\n" +
                "  background-color: #ffffff;\n" +
                "}\n" +
                "/* IMPORTANT THIS STYLES MUST BE ON FINAL EMAIL */\n" +
                ".es-button-border:hover > a.es-button {\n" +
                "  color: #ffffff !important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "  display: none;\n" +
                "  float: left;\n" +
                "  overflow: hidden;\n" +
                "  width: 0;\n" +
                "  max-height: 0;\n" +
                "  line-height: 0;\n" +
                "  mso-hide: all;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "  color: inherit !important;\n" +
                "  text-decoration: none !important;\n" +
                "  font-size: inherit !important;\n" +
                "  font-family: inherit !important;\n" +
                "  font-weight: inherit !important;\n" +
                "  line-height: inherit !important;\n" +
                "}\n" +
                "a.es-button {\n" +
                "  mso-style-priority: 100 !important;\n" +
                "  text-decoration: none !important;\n" +
                "}\n" +
                "span.MsoHyperlink,\n" +
                "span.MsoHyperlinkFollowed {\n" +
                "  color: inherit;\n" +
                "  mso-style-priority: 99;\n" +
                "}\n" +
                "#outlook a {\n" +
                "  padding: 0;\n" +
                "}\n" +
                "u + .body img ~ div div {\n" +
                "  display: none;\n" +
                "}\n" +
                ".rollover span {\n" +
                "  font-size: 0px;\n" +
                "}\n" +
                ".rollover:hover .rollover-second {\n" +
                "  max-height: none !important;\n" +
                "  display: block !important;\n" +
                "}\n" +
                ".rollover:hover .rollover-first {\n" +
                "  max-height: 0px !important;\n" +
                "  display: none !important;\n" +
                "}\n" +
                "/* END OF IMPORTANT */\n" +
                "p,\n" +
                "hr {\n" +
                "  Margin: 0;\n" +
                "}\n" +
                "h1,\n" +
                "h2,\n" +
                "h3,\n" +
                "h4,\n" +
                "h5,\n" +
                "h6 {\n" +
                "  Margin: 0;\n" +
                "  font-family: arial, 'helvetica neue', helvetica, sans-serif;\n" +
                "  mso-line-height-rule: exactly;\n" +
                "  letter-spacing: 0;\n" +
                "}\n" +
                "body {\n" +
                "  width: 100%;\n" +
                "  height: 100%;\n" +
                "}\n" +
                "table {\n" +
                "  mso-table-lspace: 0pt;\n" +
                "  mso-table-rspace: 0pt;\n" +
                "  border-collapse: collapse;\n" +
                "  border-spacing: 0px;\n" +
                "}\n" +
                "table td,\n" +
                "body,\n" +
                ".es-wrapper {\n" +
                "  padding: 0;\n" +
                "  Margin: 0;\n" +
                "}\n" +
                ".es-content,\n" +
                ".es-header,\n" +
                ".es-footer {\n" +
                "  width: 100%;\n" +
                "  table-layout: fixed !important;\n" +
                "}\n" +
                "img {\n" +
                "  display: block;\n" +
                "  font-size: 16px;\n" +
                "  border: 0;\n" +
                "  outline: none;\n" +
                "  text-decoration: none;\n" +
                "}\n" +
                "p,\n" +
                "a {\n" +
                "  mso-line-height-rule: exactly;\n" +
                "}\n" +
                ".es-left {\n" +
                "  float: left;\n" +
                "}\n" +
                ".es-right {\n" +
                "  float: right;\n" +
                "}\n" +
                ".es-menu td {\n" +
                "  border: 0;\n" +
                "}\n" +
                ".es-menu td a img {\n" +
                "  display: inline !important;\n" +
                "  vertical-align: middle;\n" +
                "}\n" +
                "s {\n" +
                "  text-decoration: line-through;\n" +
                "}\n" +
                "ul, ol {\n" +
                "  font-family: 'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;\n" +
                "  padding: 0px 0px 0px 40px;\n" +
                "  margin: 15px 0px;\n" +
                "}\n" +
                "ul li {\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                "ol li {\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                "li {\n" +
                "  margin: 0px 0px 15px;\n" +
                "  font-size: 16px;\n" +
                "}\n" +
                "a {\n" +
                "  text-decoration: none;\n" +
                "}\n" +
                ".es-menu td a {\n" +
                "  font-family: 'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;\n" +
                "  text-decoration: none;\n" +
                "  display: block;\n" +
                "}\n" +
                ".es-wrapper {\n" +
                "  width: 100%;\n" +
                "  height: 100%;\n" +
                "  background-repeat: repeat;\n" +
                "  background-position: center top;\n" +
                "}\n" +
                ".es-wrapper-color,\n" +
                ".es-wrapper {\n" +
                "  background-color: #41B9EF;\n" +
                "}\n" +
                ".es-content-body p,\n" +
                ".es-footer-body p,\n" +
                ".es-header-body p,\n" +
                ".es-infoblock p {\n" +
                "  font-family: 'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;\n" +
                "  line-height: 150%;\n" +
                "  letter-spacing: 0;\n" +
                "}\n" +
                ".es-header {\n" +
                "  background-color: transparent;\n" +
                "  background-repeat: repeat;\n" +
                "  background-position: center top;\n" +
                "}\n" +
                ".es-header-body {\n" +
                "  background-color: #1D9AD1;\n" +
                "}\n" +
                ".es-header-body p {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 14px;\n" +
                "}\n" +
                ".es-header-body a {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 14px;\n" +
                "}\n" +
                ".es-footer {\n" +
                "  background-color: transparent;\n" +
                "  background-repeat: repeat;\n" +
                "  background-position: center top;\n" +
                "}\n" +
                ".es-footer-body {\n" +
                "  background-color: #1D9AD1;\n" +
                "}\n" +
                ".es-footer-body p {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 12px;\n" +
                "}\n" +
                ".es-footer-body a {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 12px;\n" +
                "}\n" +
                ".es-content-body {\n" +
                "  background-color: #1D9AD1;\n" +
                "}\n" +
                ".es-content-body p {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 16px;\n" +
                "}\n" +
                ".es-content-body a {\n" +
                "  color: #ffffff;\n" +
                "  font-size: 16px;\n" +
                "}\n" +
                ".es-infoblock p {\n" +
                "  font-size: 12px;\n" +
                "  color: #cccccc;\n" +
                "}\n" +
                ".es-infoblock a {\n" +
                "  font-size: 12px;\n" +
                "  color: #cccccc;\n" +
                "}\n" +
                "h1 {\n" +
                "  font-size: 40px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: bold;\n" +
                "  line-height: 120%;\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                ".es-header-body h1 a,\n" +
                ".es-content-body h1 a,\n" +
                ".es-footer-body h1 a {\n" +
                "  font-size: 40px;\n" +
                "}\n" +
                "h2 {\n" +
                "  font-size: 28px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: bold;\n" +
                "  line-height: 120%;\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                ".es-header-body h2 a,\n" +
                ".es-content-body h2 a,\n" +
                ".es-footer-body h2 a {\n" +
                "  font-size: 28px;\n" +
                "}\n" +
                "h3 {\n" +
                "  font-size: 20px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: bold;\n" +
                "  line-height: 120%;\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                ".es-header-body h3 a,\n" +
                ".es-content-body h3 a,\n" +
                ".es-footer-body h3 a {\n" +
                "  font-size: 20px;\n" +
                "}\n" +
                "h4 {\n" +
                "  font-size: 24px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: normal;\n" +
                "  line-height: 120%;\n" +
                "  color: #333333;\n" +
                "}\n" +
                ".es-header-body h4 a,\n" +
                ".es-content-body h4 a,\n" +
                ".es-footer-body h4 a {\n" +
                "  font-size: 24px;\n" +
                "}\n" +
                "h5 {\n" +
                "  font-size: 20px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: normal;\n" +
                "  line-height: 120%;\n" +
                "  color: #333333;\n" +
                "}\n" +
                ".es-header-body h5 a,\n" +
                ".es-content-body h5 a,\n" +
                ".es-footer-body h5 a {\n" +
                "  font-size: 20px;\n" +
                "}\n" +
                "h6 {\n" +
                "  font-size: 16px;\n" +
                "  font-style: normal;\n" +
                "  font-weight: normal;\n" +
                "  line-height: 120%;\n" +
                "  color: #333333;\n" +
                "}\n" +
                ".es-header-body h6 a,\n" +
                ".es-content-body h6 a,\n" +
                ".es-footer-body h6 a {\n" +
                "  font-size: 16px;\n" +
                "}\n" +
                "a.es-button, button.es-button {\n" +
                "  padding: 15px 30px 15px 30px;\n" +
                "  display: inline-block;\n" +
                "  background: #F76B0A;\n" +
                "  border-radius: 30px 30px 30px 30px;\n" +
                "  font-size: 20px;\n" +
                "  font-family: arial, 'helvetica neue', helvetica, sans-serif;\n" +
                "  font-weight: normal;\n" +
                "  font-style: normal;\n" +
                "  line-height: 120%;\n" +
                "  color: #ffffff;\n" +
                "  text-decoration: none !important;\n" +
                "  width: auto;\n" +
                "  text-align: center;\n" +
                "  letter-spacing: 0;\n" +
                "  mso-padding-alt: 0;\n" +
                "  mso-border-alt: 10px solid #F76B0A;\n" +
                "}\n" +
                ".es-button-border {\n" +
                "  border-style: solid;\n" +
                "  border-color: #e29f33 #2cb543 #2cb543 #e29f33;\n" +
                "  background: #F76B0A;\n" +
                "  border-width: 2px 0px 0px 2px;\n" +
                "  display: inline-block;\n" +
                "  border-radius: 30px 30px 30px 30px;\n" +
                "  width: auto;\n" +
                "}\n" +
                ".es-button img {\n" +
                "  display: inline-block;\n" +
                "  vertical-align: middle;\n" +
                "}\n" +
                ".es-fw,\n" +
                ".es-fw .es-button {\n" +
                "  display: block;\n" +
                "}\n" +
                ".es-il,\n" +
                ".es-il .es-button {\n" +
                "  display: inline-block;\n" +
                "}\n" +
                ".es-text-rtl h1,\n" +
                ".es-text-rtl h2,\n" +
                ".es-text-rtl h3,\n" +
                ".es-text-rtl h4,\n" +
                ".es-text-rtl h5,\n" +
                ".es-text-rtl h6,\n" +
                ".es-text-rtl input,\n" +
                ".es-text-rtl label,\n" +
                ".es-text-rtl textarea,\n" +
                ".es-text-rtl p,\n" +
                ".es-text-rtl ol,\n" +
                ".es-text-rtl ul,\n" +
                ".es-text-rtl .es-menu a,\n" +
                ".es-text-rtl .es-table {\n" +
                "  direction: rtl;\n" +
                "}\n" +
                ".es-text-ltr h1,\n" +
                ".es-text-ltr h2,\n" +
                ".es-text-ltr h3,\n" +
                ".es-text-ltr h4,\n" +
                ".es-text-ltr h5,\n" +
                ".es-text-ltr h6,\n" +
                ".es-text-ltr input,\n" +
                ".es-text-ltr label,\n" +
                ".es-text-ltr textarea,\n" +
                ".es-text-ltr p,\n" +
                ".es-text-ltr ol,\n" +
                ".es-text-ltr ul,\n" +
                ".es-text-ltr .es-menu a,\n" +
                ".es-text-ltr .es-table {\n" +
                "  direction: ltr;\n" +
                "}\n" +
                ".es-text-rtl ol, .es-text-rtl ul {\n" +
                "  padding: 0px 40px 0px 0px;\n" +
                "}\n" +
                ".es-text-ltr ul, .es-text-ltr ol {\n" +
                "  padding: 0px 0px 0px 40px;\n" +
                "}\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "  .h-auto {\n" +
                "    height: auto !important;\n" +
                "  }\n" +
                "  .es-social td {\n" +
                "    padding-bottom: 10px;\n" +
                "  }\n" +
                "  table.es-table-not-adapt,\n" +
                "  .esd-block-html table {\n" +
                "    width: auto !important;\n" +
                "  }\n" +
                "  td.es-desk-menu-hidden {\n" +
                "    display: table-cell !important;\n" +
                "  }\n" +
                "  table.es-desk-hidden {\n" +
                "    display: table !important;\n" +
                "  }\n" +
                "  tr.es-desk-hidden {\n" +
                "    display: table-row !important;\n" +
                "  }\n" +
                "  .es-mobile-hidden,\n" +
                "  .es-hidden {\n" +
                "    display: none !important;\n" +
                "  }\n" +
                "  .adapt-img {\n" +
                "    width: 100% !important;\n" +
                "    height: auto !important;\n" +
                "  }\n" +
                "  .adapt-img img {\n" +
                "    width: 100% !important;\n" +
                "    height: auto !important;\n" +
                "  }\n" +
                "  table.es-table, .esd-block-html table {\n" +
                "    width: 100% !important;\n" +
                "    table-layout: fixed !important;\n" +
                "  }\n" +
                "  table.es-social {\n" +
                "    display: inline-block !important;\n" +
                "  }\n" +
                "  table.es-social td {\n" +
                "    display: inline-block !important;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n"+
                "</head>\n" +
                "<body class=\"body\">\n" +
                "  <div dir=\"ltr\" class=\"es-wrapper-color\">\n" +
                "   <!--[if gte mso 9]>\n" +
                "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "				<v:fill type=\"tile\" color=\"#41B9EF\"></v:fill>\n" +
                "			</v:background>\n" +
                "		<![endif]-->\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" esd-img-prev-position=\"center top\" style=\"background-position: center top;\">\n" +
                "    <tbody>\n" +
                "     <tr>\n" +
                "      <td class=\"esd-email-paddings\" valign=\"top\">\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"esd-header-popover es-header\" align=\"center\">\n" +
                "        <tbody>\n" +
                "         <tr>\n" +
                "          <td class=\"esd-stripe\" align=\"center\">\n" +
                "           <table class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"560\" style=\"border-radius: 10px 10px 0px 0px;\">\n" +
                "            <tbody>\n" +
                "             <tr>\n" +
                "              <td class=\"esd-structure es-p20\" align=\"left\">\n" +
                "               <!--[if mso]><table width=\"520\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"250\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"es-left\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"250\" class=\"es-m-p0r esd-container-frame es-m-p20b\" valign=\"top\" align=\"center\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"esd-block-image es-m-txt-c\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img src=\"https://ehnhbhg.stripocdn.email/content/guids/CABINET_95d6ab65a170f3e2567f04fb3b1cb14b/images/group.png\" alt=\"Logo\" style=\"display: block;\" title=\"Logo\" width=\"58\"></a></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table>\n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"250\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"250\" align=\"left\" class=\"esd-container-frame\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"right\" class=\"esd-block-text es-m-txt-c\"><h3>​</h3></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table>\n" +
                "               <!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "             <tr>\n" +
                "              <td class=\"esd-structure es-p5t es-p5b\" align=\"left\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"560\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\"><img class=\"adapt-img\" src=\"https://ehnhbhg.stripocdn.email/content/guids/CABINET_4bcca2afc9156fbbd6e09babbb92c2d0/images/group_37_rH7.png\" alt=\"\" style=\"display: block;\" width=\"560\"></a></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "            </tbody>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "        </tbody>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
                "        <tbody>\n" +
                "         <tr>\n" +
                "          <td class=\"esd-stripe\" align=\"center\">\n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"560\" style=\"border-radius: 0 0 10px 10px;\">\n" +
                "            <tbody>\n" +
                "             <tr>\n" +
                "              <td class=\"esd-structure es-p20t es-p30b es-p20l\" align=\"left\">\n" +
                "               <!--[if mso]><table width=\"540\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"327\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"es-left\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"327\" class=\"es-m-p0r esd-container-frame es-m-p20b\" valign=\"top\" align=\"center\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"esd-block-text es-p20t es-p10b es-m-p20r es-m-txt-l\"><h1>Attention Your project is at risk of being delayed</h1></td>\n" +
                "                     </tr>\n" +
                "                     \n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"esd-block-text es-p10t es-p10b es-p10r es-m-p20r\"><h3 style=\"color: #f76b0a;\"><span style=\"background-color:#FFFFFF; border-radius: 10px;\">Task No. \"" +task.getTaskid()+ "\" </span></h3></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table>\n" +
                "               <!--[if mso]></td><td width=\"5\"></td><td width=\"208\" valign=\"top\"><![endif]-->\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"208\" align=\"left\" class=\"esd-container-frame\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"mailto:malek.frikhi@esprit.tn\"><img class=\"adapt-img\" src=\"https://ehnhbhg.stripocdn.email/content/guids/CABINET_4bcca2afc9156fbbd6e09babbb92c2d0/images/5883092.png\" alt=\"photo\" title=\"photo\" style=\"display: block;\" width=\"208\"></a></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table>\n" +
                "               <!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "            </tbody>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "        </tbody>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
                "        <tbody>\n" +
                "         <tr>\n" +
                "          <td class=\"esd-stripe\" align=\"center\">\n" +
                "           <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"560\" style=\"background-color: transparent;\">\n" +
                "            <tbody>\n" +
                "             <tr>\n" +
                "              <td class=\"esd-structure es-p30t es-p20b es-p20r es-p20l\" align=\"left\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"esd-block-text\"><h2>Hi,\"" + task.getProjetT().getProjectManager().getFirstname()+ "\"</h2></td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                      <td align=\"left\" class=\"esd-block-text es-p10t es-p10b es-p10r es-m-p20r\"><p><br>The task \"" + task.getTaskDescription() + "\" assigned to " + task.getEmployeeTask().getFirstname() + " is overdue. You need to catch up on this delay, otherwise your project is at risk of being delayed.</p></td>" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "            </tbody>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "        </tbody>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
                "        <tbody>\n" +
                "         <tr>\n" +
                "          <td class=\"esd-stripe\" align=\"center\">\n" +
                "           <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"560\" style=\"background-color: transparent;\">\n" +
                "            <tbody>\n" +
                "             <tr>\n" +
                "              <td class=\"esd-structure es-p20t es-p40b es-p20r es-p20l\" align=\"left\">\n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                <tbody>\n" +
                "                 <tr>\n" +
                "                  <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                     <tr>\n" +
                "                      <td align=\"center\" class=\"esd-block-button es-m-txt-c\"><span class=\"es-button-border\" style=\"background: #f76b0a;\"><a href=\"http://localhost:4200/Project/getAllProject\" class=\"es-button\" target=\"_blank\">Manage Your Project</a></span></td>\n" +
                "                     </tr>\n" +
                "                    </tbody>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "                </tbody>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "            </tbody>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "        </tbody>\n" +
                "       </table>\n" +
                "       </td>\n" +
                "     </tr>\n" +
                "    </tbody>\n" +
                "   </table>\n" +
                "  </div>\n" +
                " \n" +
                "\n" +
                "  </body></html>";


        return htmlBody;
    }


}

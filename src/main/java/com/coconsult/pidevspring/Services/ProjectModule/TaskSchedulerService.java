package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.EmailDetails;
import com.coconsult.pidevspring.DAO.Entities.StatusTask;
import com.coconsult.pidevspring.DAO.Entities.Task;
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

    @Scheduled(cron = "0 0 0 * * ?") // Exécute une fois par jour à minuit
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
                sendEmailToProjectManager(task);
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

    private void sendEmailToProjectManager(Task task) {
        // Créer et envoyer l'e-mail
        EmailDetails emailDetails = createEmailDetails(task);
        emailService.sendSimpleEmail(emailDetails.getTo(), emailDetails.getSubject(), emailDetails.getBody());
    }

    private EmailDetails createEmailDetails(Task task) {
        // Créer le contenu de l'e-mail
        String subject = "Tâche en retard";
        String body = "La tâche \"" + task.getTaskname() + "\" est en retard. Vous devez récupérer ce retard sinon votre projet risque de se retarder.";
        String to = "malek.frikhi@esprit.tn"; // Remplacez par l'adresse e-mail du gestionnaire de projet
        return new EmailDetails(to, subject, body);
    }
}

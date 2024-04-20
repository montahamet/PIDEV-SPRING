package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.ActionLog;
import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Repository.ActionLogRepository;
import com.coconsult.pidevspring.DAO.Repository.ProjectOfferRepository;
import com.coconsult.pidevspring.DAO.Repository.QuoteRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class ProjectOfferService implements IProjectOfferService{

    ProjectOfferRepository projectOfferRepository;
    QuoteRepository quoteRepository;
    private final ActionLogRepository actionLogRepository;

    @Override
    public List<ProjectOffer> retrieveAllProjectOffer() {


        return projectOfferRepository.findAll();
    }

    @Override
    public ProjectOffer retrieveProjectOffer(Long projectOfferId) {
        ProjectOffer projectOffer = projectOfferRepository.findById(projectOfferId).get();
//        logAction("GET", projectOffer.getProjectTitle(), projectOfferId); // Log action for getting all feedbacks

        return projectOfferRepository.findById(projectOfferId).get();
    }

    @Override
    public ProjectOffer addProjectOffer(ProjectOffer projectOffer) {
//        Set<Quote> quotes=projectOffer.getQuotes();
//        for (Quote quote : quotes) {
//            quoteRepository.save(quote);
//        }
//        projectOffer.setQuotes(quotes);
        ProjectOffer projectOffer1=projectOfferRepository.save(projectOffer);
        logAction("CREATE", projectOffer1.getProjectTitle(), projectOffer1.getOfferId()); // Log action for getting all feedbacks

        return projectOffer1 ;
    }

    @Override
    public void removeProjectOffer(Long projectOfferId) {
        ProjectOffer projectOffer = projectOfferRepository.findById(projectOfferId).get();
        logAction("DELETE", projectOffer.getProjectTitle(), projectOfferId); // Log deletion action
        List<Quote> quotes= quoteRepository.findByProjectofferquoteOfferId(projectOfferId);
        for (Quote quote : quotes) {
            quote.setProjectofferquote(null);
            quote.setProjectOfferId(null);
            quoteRepository.save(quote);
        }
        projectOfferRepository.deleteById(projectOfferId);
    }

    @Override
    public ProjectOffer modifyProjectOffer(ProjectOffer projectOffer) {

        ProjectOffer projectOffer1 = projectOfferRepository.save(projectOffer);
        logAction("UPDATE", projectOffer1.getProjectTitle(), projectOffer1.getOfferId()); // Log update action

        return projectOffer1 ;
    }

    private void logAction(String action, String entityName, Long entityId) {
        int userid = 1;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = getIpAddress(request);
        String httpMethod = getHttpMethod();
        String requestUri = getRequestUri();

        ActionLog log = ActionLog.builder()
                .action(action)
                .entityName(entityName)
                .entityId(entityId)
                .userid(userid)
                .ipAddress(ipAddress)
                .httpMethod(httpMethod)
                .requestUri(requestUri)
                .timestamp(LocalDateTime.now())
                .build();
        actionLogRepository.save(log);
    }

//    private String getUsername() {
//        String username = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            username = ((User) principal).getUsername();
//        } else if (principal instanceof String) {
//            username = (String) principal;
//        }
//        return username;
//    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("RemoteAddr");
        }
        return ipAddress;
    }


    private String getHttpMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getMethod();
    }

    private String getRequestUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRequestURI();
    }

}

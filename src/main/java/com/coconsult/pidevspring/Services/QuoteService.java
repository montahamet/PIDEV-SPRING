package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.ActionLog;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Repository.ActionLogRepository;
import com.coconsult.pidevspring.DAO.Repository.QuoteRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QuoteService implements IQuoteService{

    QuoteRepository quoteRepository;
    private final ActionLogRepository actionLogRepository;


    public long countQuotesByProjectOffer(Long projectOfferId) {
        return quoteRepository.countByProjectofferquoteOfferId(projectOfferId);

    }
    @Override
    public List<Quote> retrieveAllQuote() {
//        logAction("GET_ALL", "Quotes", null); // Log action for getting all feedbacks

        return quoteRepository.findAll();
    }
    @Override
    public List<Quote> retrieveAllQuoteNotNull() {
//        logAction("GET_ALL", "Quotes", null); // Log action for getting all feedbacks

        return quoteRepository.findQuoteByProjectOfferIdNotNull();
    }

    @Override
    public Quote retrieveQuote(Long quoteId) {

//        logAction("GET", "Quote", quoteId); // Log action for getting all feedbacks

        return quoteRepository.findById(quoteId).get();
    }

    @Override
    public Quote addQuote(Quote quote) {

        quote.setProjectOfferId(quote.getProjectofferquote().getOfferId());
        Quote createdquote= quoteRepository.save(quote);
        logAction("QUOTE"+createdquote.getQuote_id()+" AFFECTED", createdquote.getProjectofferquote().getProjectTitle(), createdquote.getProjectofferquote().getOfferId()); // Log action for getting all feedbacks

        return createdquote;
    }

    @Override
    public void removeQuote(Long quoteId) {
//        logAction("DELETE", "Quote", quoteId); // Log deletion action
        Quote quote = quoteRepository.findById(quoteId).get();

        logAction("QUOTE"+quote.getQuote_id()+" DELETED", quote.getProjectofferquote().getProjectTitle(), quote.getProjectofferquote().getOfferId()); // Log deletion action



        quoteRepository.deleteById(quoteId);
    }

    @Override
    public Quote modifyQuote(Quote quote) {
        quote.setProjectOfferId(quote.getProjectofferquote().getOfferId());

//        quote.setProjectOfferId(quote.getProjectofferquote().getOfferId());

        Quote quote1= quoteRepository.save(quote);
        logAction("QUOTE"+quote1.getQuote_id()+"UPDATED", quote1.getProjectofferquote().getProjectTitle(), quote1.getProjectofferquote().getOfferId()); // Log update action

        return quote1;
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

    //logs section
}

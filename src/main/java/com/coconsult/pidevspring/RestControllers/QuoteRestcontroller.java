package com.coconsult.pidevspring.RestControllers;


import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.ProjectOfferStatus;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.IProjectOfferService;
import com.coconsult.pidevspring.Services.IQuoteService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController

@RequestMapping("/quote")
@CrossOrigin("*")
public class QuoteRestcontroller {
    IQuoteService iQuoteService;
    private final IProjectOfferService iProjectOfferService; // Inject the ProjectOffer service




    @GetMapping("/retrieve-quotes")
    public List<Quote> retrieveAllQuote() {
        List<Quote> quotes= iQuoteService.retrieveAllQuote();
        return quotes;
    }
    @GetMapping("/retrieve-quotes-not-null")
    public List<Quote> retrieveAllQuoteNotNull() {
        List<Quote> quotes= iQuoteService.retrieveAllQuoteNotNull();
        return quotes;
    }

    @Autowired
    public QuoteRestcontroller(IQuoteService iQuoteService, IProjectOfferService iProjectOfferService) {
        this.iQuoteService = iQuoteService;
        this.iProjectOfferService = iProjectOfferService;
    }


    @PostMapping("/addquote/{projectOfferId}")
    public ResponseEntity<?> addQuote(@RequestBody Quote quote, @PathVariable("projectOfferId") Long projectOfferId) {
        // Fetch the ProjectOffer entity based on the projectOfferId provided in the Quote object
        ProjectOffer projectOffer = iProjectOfferService.retrieveProjectOffer(projectOfferId);

        // Check if the project offer's status is ACCEPTED
        if (projectOffer.getStatus() == ProjectOfferStatus.ACCEPTED) {
            return new ResponseEntity<>("Quotes cannot be added to an accepted project offer.", HttpStatus.BAD_REQUEST);
        }

        // Check if the project offer already has 5 quotes
        long existingQuotesCount = iQuoteService.countQuotesByProjectOffer(projectOfferId);
        if (existingQuotesCount >= 6) {
            return new ResponseEntity<>("Maximum number of quotes reached for this project offer.", HttpStatus.BAD_REQUEST);
        }



        projectOffer.getQuotes().add(quote);

        // Set the fetched ProjectOffer entity on the Quote entity
        quote.setProjectofferquote(projectOffer);

        // Save the Quote entity with the associated ProjectOffer
        Quote savedQuote = iQuoteService.addQuote(quote);
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }


    //    @PostMapping("/addquote/{projectOfferId}")
//    public Quote addQuote(@RequestBody Quote quote, @PathVariable("projectOfferId") Long projectOfferId) {   // Fetch the ProjectOffer entity based on the projectOfferId provided in the Quote object
//        ProjectOffer projectOffer = iProjectOfferService.retrieveProjectOffer(projectOfferId);
//
////         Set the fetched ProjectOffer entity on the Quote entity
//        quote.setProjectofferquote(projectOffer);
////        Long projectofferquoteOfferId = new ProjectOffer().getOffer_id();
//        // Now, save the Quote entity with the associated ProjectOffer
//        return iQuoteService.addQuote(quote);
//    }
    @PutMapping("/updatequote/{projectOfferId}")
    public Quote updateQuote(@RequestBody Quote quote, @PathVariable("projectOfferId") Long projectOfferId) {
        ProjectOffer projectOffer = iProjectOfferService.retrieveProjectOffer(projectOfferId);
        projectOffer.getQuotes().add(quote);

        // Set the fetched ProjectOffer entity on the Quote entity
        quote.setProjectofferquote(projectOffer);

        return iQuoteService.modifyQuote(quote);
    }

    @GetMapping("/retrievequote/{quoteid}")
    public Quote retrieveQuote(@PathVariable("quoteid") Long quoteid) {
        Quote quote = iQuoteService.retrieveQuote(quoteid);
        return quote;
    }


    @DeleteMapping("/removequote/{quoteid}")
    public void removeQuote(@PathVariable ("quoteid") Long quoteid) {
        iQuoteService.removeQuote(quoteid);
    }




}

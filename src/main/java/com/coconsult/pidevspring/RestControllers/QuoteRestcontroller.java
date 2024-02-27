package com.coconsult.pidevspring.RestControllers;


import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.Services.IQuoteService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quote")
public class QuoteRestcontroller {
    IQuoteService iQuoteService;



    @GetMapping("/retrieve-quotes")
    public List<Quote> retrieveAllQuote() {
        List<Quote> quotes= iQuoteService.retrieveAllQuote();
        return quotes;
    }

    @PostMapping("/add-quote")
    public Quote addQuote(Quote quote) {
        return iQuoteService.addQuote(quote);
    }


    @PutMapping("/update-quote")
    public Quote updateQuote(@RequestBody Quote quote) {
        return iQuoteService.modifyQuote(quote);
    }

    @GetMapping("/retrieve-quote")
    public Quote retrieveQuote(@PathParam("quoteid") Long quoteid) {
        Quote quote = iQuoteService.retrieveQuote(quoteid);
        return quote;
    }


    @DeleteMapping("/remove-quote")
    public void removeQuote(@PathParam ("quoteid") Long quoteid) {
        iQuoteService.removeQuote(quoteid);
    }




}

package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Repository.QuoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuoteService implements IQuoteService{

    QuoteRepository quoteRepository;
    @Override
    public List<Quote> retrieveAllQuote() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote retrieveQuote(Long quoteId) {
        return quoteRepository.findById(quoteId).get();
    }

    @Override
    public Quote addQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void removeQuote(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    @Override
    public Quote modifyQuote(Quote quote) {
        Quote quote1=quoteRepository.save(quote);
        return quote1;
    }
}

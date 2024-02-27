package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Entities.Quote;

import java.util.List;

public interface IQuoteService {

    public List<Quote> retrieveAllQuote();
    public Quote retrieveQuote(Long quoteId);
    public Quote addQuote(Quote quote);
    public void removeQuote(Long quoteId);
    public Quote modifyQuote(Quote quote);
}

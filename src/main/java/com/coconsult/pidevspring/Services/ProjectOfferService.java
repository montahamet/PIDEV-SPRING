package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.DAO.Repository.ProjectOfferRepository;
import com.coconsult.pidevspring.DAO.Repository.QuoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class ProjectOfferService implements IProjectOfferService{

    ProjectOfferRepository projectOfferRepository;
    QuoteRepository quoteRepository;
    @Override
    public List<ProjectOffer> retrieveAllProjectOffer() {
        return projectOfferRepository.findAll();
    }

    @Override
    public ProjectOffer retrieveProjectOffer(Long projectOfferId) {
        return projectOfferRepository.findById(projectOfferId).get();
    }

    @Override
    public ProjectOffer addProjectOffer(ProjectOffer projectOffer) {
//        Set<Quote> quotes=projectOffer.getQuotes();
//        for (Quote quote : quotes) {
//            quoteRepository.save(quote);
//        }
//        projectOffer.setQuotes(quotes);
        return projectOfferRepository.save(projectOffer);
    }

    @Override
    public void removeProjectOffer(Long projectOfferId) {
            projectOfferRepository.deleteById(projectOfferId);
    }

    @Override
    public ProjectOffer modifyProjectOffer(ProjectOffer projectOffer) {
        return projectOfferRepository.save(projectOffer);
    }
}

package com.coconsult.pidevspring.DAO.Repository;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuoteRepository  extends JpaRepository<Quote,Long> {
    List<Quote> findQuoteByProjectOfferIdNotNull();
    long countByProjectofferquoteOfferId(Long offerId);
    List<Quote> findByProjectofferquoteOfferId(Long offerId);

    @Query("SELECT q FROM Quote q")
    @EntityGraph(attributePaths = {"projectofferquote"})
    List<Quote> findAllWithProjectOffer();
}

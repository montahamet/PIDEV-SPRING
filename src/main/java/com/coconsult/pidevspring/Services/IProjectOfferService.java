package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.Attendence;
import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;

import java.util.List;

public interface IProjectOfferService {

    public List<ProjectOffer> retrieveAllProjectOffer();
    public ProjectOffer retrieveProjectOffer(Long projectOfferId);
    public ProjectOffer addProjectOffer(ProjectOffer projectOffer);
    public void removeProjectOffer(Long projectOfferId);
    public ProjectOffer modifyProjectOffer(ProjectOffer projectOffer);
}

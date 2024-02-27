package com.coconsult.pidevspring.DAO.Repository;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectOfferRepository extends JpaRepository<ProjectOffer,Long> {
}

package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.Services.IProjectOfferService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projectoffer")
public class ProjectOfferRestcontroller {
    IProjectOfferService iProjectOfferService;

    @GetMapping("/retrieve-projectoffers")
    public List<ProjectOffer> retrieveAllProjectOffer() {
        List<ProjectOffer> projectOffers= iProjectOfferService.retrieveAllProjectOffer();
        return projectOffers;
    }

    @PostMapping("/add-projectoffer")
    public ProjectOffer addProjectOffer(@RequestBody ProjectOffer projectOffer) {
        ProjectOffer projectOffer1 = iProjectOfferService.addProjectOffer(projectOffer);

        return projectOffer1 ;
    }


    @PutMapping("/update-projectoffer")
    public ProjectOffer updateProjectOffer(@RequestBody ProjectOffer projectOffer) {
        return iProjectOfferService.modifyProjectOffer(projectOffer);
    }

    @GetMapping("/retrieve-projectoffer")
    public ProjectOffer retrieveProjectOffer(@PathParam("projectofferid") Long projectofferid) {
        ProjectOffer projectOffer = iProjectOfferService.retrieveProjectOffer(projectofferid);
        return projectOffer;
    }


    @DeleteMapping("/remove-projectoffer")
    public void removeProjectOffer(@PathParam("projectofferid") Long projectofferid) {
        iProjectOfferService.removeProjectOffer(projectofferid);
    }


}

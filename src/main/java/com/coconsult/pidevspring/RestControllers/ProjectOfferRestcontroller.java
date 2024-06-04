package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.ProjectOffer;
import com.coconsult.pidevspring.DAO.Entities.Quote;
import com.coconsult.pidevspring.Services.EmailServiceProjectoffer;
import com.coconsult.pidevspring.Services.IProjectOfferService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/projectoffer")
public class ProjectOfferRestcontroller {
    IProjectOfferService iProjectOfferService;
    EmailServiceProjectoffer emailServiceProjectoffer;

    @GetMapping("/retrieve-projectoffers")
    public List<ProjectOffer> retrieveAllProjectOffer() {
        List<ProjectOffer> projectOffers= iProjectOfferService.retrieveAllProjectOffer();
        return projectOffers;
    }

    //    @PostMapping("/addprojectoffer")
//    public ProjectOffer addProjectOffer(@RequestBody ProjectOffer projectOffer) {
//        ProjectOffer projectOffer1 = iProjectOfferService.addProjectOffer(projectOffer);
//
//        return projectOffer1 ;
//    }
    @PostMapping("/addprojectoffer")
    public ProjectOffer addProjectOffer(@RequestBody ProjectOffer projectOffer) {
        ProjectOffer addedProjectOffer = iProjectOfferService.addProjectOffer(projectOffer);

        // Send email with project offer details
        emailServiceProjectoffer.send(projectOffer.getCompanyemail(), addedProjectOffer);

        return addedProjectOffer;
    }



    @PutMapping("/updateprojectoffer")
    public ProjectOffer updateProjectOffer(@RequestBody ProjectOffer projectOffer) {
        return iProjectOfferService.modifyProjectOffer(projectOffer);
    }

    @GetMapping("/retrieveprojectoffer/{projectofferid}")
    public ProjectOffer retrieveProjectOffer(@PathVariable("projectofferid") Long projectofferid) {
        ProjectOffer projectOffer = iProjectOfferService.retrieveProjectOffer(projectofferid);
        return projectOffer;
    }


    @DeleteMapping("/removeprojectoffer/{projectofferid}")
    public void removeProjectOffer(@PathVariable("projectofferid") Long projectofferid) {
        iProjectOfferService.removeProjectOffer(projectofferid);
    }


}
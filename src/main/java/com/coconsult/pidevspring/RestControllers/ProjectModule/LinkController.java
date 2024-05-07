package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Link;
import com.coconsult.pidevspring.Services.ProjectModule.ILinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/Link")
public class LinkController {
    ILinkService iLinkService;
    @GetMapping("/GetAllLinks")
    public List<Link> getAllLinks() {
        return iLinkService.getAllLinks();
    }
    @PostMapping("/AddLink")
    public Link addLink(@RequestBody Link link) {
        return iLinkService.addLink(link);
    }

    @PutMapping("/UpdateLink")
    public Link updateLink(@RequestBody Link link) {
        return iLinkService.updateLink(link);
    }

    @DeleteMapping("/DeleteLinkbyid")
    public void removeLink(@RequestParam long id) {
        iLinkService.removeLink(id);
    }

}

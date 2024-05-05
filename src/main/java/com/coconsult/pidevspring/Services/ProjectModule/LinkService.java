package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Link;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.LinkRepository;
import com.coconsult.pidevspring.DAO.Repository.ProjectModule.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LinkService implements ILinkService {
    @Autowired
    LinkRepository linkRepository;

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public Link addLink(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public Link updateLink(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public void removeLink(long id) {
        linkRepository.deleteById(id);

    }
}

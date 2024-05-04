package com.coconsult.pidevspring.Services.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.Link;

import java.util.List;

public interface ILinkService {
    List<Link> getAllLinks();

    Link addLink(Link link);

    Link updateLink(Link link);
    void removeLink(long id);

}

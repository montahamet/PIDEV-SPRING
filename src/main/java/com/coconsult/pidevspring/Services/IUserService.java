package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUser();

    User addUser(User u);

    User updateUser(User u);

    User retrieveOneUser(Long userId);
    User retrieveUser(Long userId);

    void removeUser(Long userId);
}

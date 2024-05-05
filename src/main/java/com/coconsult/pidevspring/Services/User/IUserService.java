package com.coconsult.pidevspring.Services.User;

import com.coconsult.pidevspring.DAO.Entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> retrieveAllUser();
    User addUser(User u);

    User updateUser(User u);
    User updateUsermdp(User u);
    User retrieveOneUser(Long userId);
    User retrieveUser(Long userId);

    void removeUser(Long userId);

   // List<User> saveusers();

    //malekkk
    List<User> getProjectManagers();
    //malekkkk
    List<User> getEmployeesForTASKS();
    // malekk
    List<User> findCompetentUsersOrderByTasks();



}

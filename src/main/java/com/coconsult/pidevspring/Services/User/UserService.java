package com.coconsult.pidevspring.Services.User;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository ;
    @Override
    public List<User> retrieveAllUser() {
        return (List<User>)  userRepository.findAll();
    }

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public User retrieveOneUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User retrieveUser(Long userId) {
        return null;
    }

    @Override
    public void removeUser(Long userID) {
        userRepository.deleteById(userID);

    }
    ///malekkk
    @Override
    public List<User> getProjectManagers() {
        return userRepository.findByRolesRoleName("PROJECT_MANAGER");
    }
    // malekkk

    @Override
    public List<User> getEmployeesForTASKS() {
        return userRepository.findByRolesRoleName("EMPLOYEE");
    }
    //malekk
    @Override
    public List<User> findCompetentUsersOrderByTasks() {
        return userRepository.findCompetentUsersOrderByTasks();
    }




}

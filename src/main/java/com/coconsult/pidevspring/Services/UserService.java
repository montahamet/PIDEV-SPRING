package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    UserRepository userRepository ;
    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
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
}

package com.coconsult.pidevspring.Services.User;

import com.coconsult.pidevspring.DAO.Entities.User;
import com.coconsult.pidevspring.DAO.Repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> retrieveAllUser() {
        return (List<User>) userRepository.findAll();
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
    public User updateUsermdp(User u) {

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
    public Long count() {
        return userRepository.countUsers();
    }

    @Override
    public Map<String, Long> countUsersByRoles() {
        List<Object[]> roleCounts = userRepository.countUsersByRoles();
        Map<String, Long> roleCountsMap = new HashMap<>();
        for (Object[] rc : roleCounts) {
            roleCountsMap.put((String) rc[0], (Long) rc[1]);
        }
        return roleCountsMap;
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
        return null;//userRepository.findCompetentUsersOrderByTasks();
    }
}
/*
@Override
public String recognizeFace(byte[] image) {
    String url = "http://localhost:5000/recognize-face";

    // Prepare request body
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("image", new ByteArrayResource(image));

    // Make POST request to Python service
    ResponseEntity<String> response = restTemplate.postForEntity(url, body, String.class);

    // Process response
    if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
    } else {
        return "Error: " + response.getStatusCodeValue();
    }
}


}*/

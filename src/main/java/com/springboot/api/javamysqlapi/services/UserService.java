package com.springboot.api.javamysqlapi.services;

import com.springboot.api.javamysqlapi.models.User;
import com.springboot.api.javamysqlapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }
    
    public User getUserName(String username) {
    	return userRepository.getUserByUsername(username);
    }
    

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
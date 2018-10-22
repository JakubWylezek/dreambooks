package com.dreambooks.service;

import com.dreambooks.model.User;
import com.dreambooks.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);

        return users;
    }

    public Long countAllUsers() {
        return userRepository.countUsers();
    }
}

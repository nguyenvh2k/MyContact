package com.myconcat.service;

import com.myconcat.entity.User;
import com.myconcat.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}

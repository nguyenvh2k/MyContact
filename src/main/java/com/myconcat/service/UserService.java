package com.myconcat.service;

import com.myconcat.entity.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAllUser();

    Optional<User> findUserByNameAndPassword(String username,String password);
}

package com.myconcat.service;

import com.myconcat.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    void saveUser(User user);
    Optional<User> findUserByNameAndPassword(String username,String password);
    void delete(Integer id);
    User update();
}

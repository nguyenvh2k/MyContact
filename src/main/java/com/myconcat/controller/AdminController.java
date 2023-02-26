package com.myconcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.myconcat.service.UserService;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id")Integer id) {
        userService.delete(id);
        return "list";
    }
    
    
}

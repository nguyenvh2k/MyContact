package com.myconcat.controller;

import com.myconcat.entity.User;
import com.myconcat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

//    @GetMapping("/test")
//    public String test(Model model){
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("admin");
//        userService.saveUser(user);
//        return "login";
//    }

    //Dang nhap
    @PostMapping("/login")
    public String loginForm(User user,Model model){
        Optional<User> user1 = userService.findUserByNameAndPassword(user.getUsername(), user.getPassword());
        User user_contact = user1.orElse(new User());
        if (!user1.isPresent()){
            System.out.println("Failed !!");
            model.addAttribute("msg","Tên đăng nhập hoặc mật khẩu không chính xác");
            return "login";
        }
        System.out.println("Success !!");
        session.setAttribute("user",user_contact);
        return "redirect:/contact";
    }

}

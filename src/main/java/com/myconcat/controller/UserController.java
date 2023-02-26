package com.myconcat.controller;

import com.myconcat.entity.User;
import com.myconcat.service.UserServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import java.util.List;
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
        String msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
        model.addAttribute("msg",msg);
        return "login";
    }

   @GetMapping("/test")
   public String test(Model model){
       User user = new User();
       user.setUsername("admin");
       user.setPassword("admin");
       user.setRole("admin");
       userService.saveUser(user);
       return "redirect/";
   }

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
        if(user_contact.getRole().equals("admin")){
            session.setAttribute("admin",user_contact);
            System.out.println("Success !!");
            return "redirect:/admin-page";
        }
        System.out.println("Success !!");
        session.setAttribute("user",user_contact);
        session.setMaxInactiveInterval(60);
        return "redirect:/contact";
    }

    @GetMapping("/signup")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", (String)session.getAttribute("error"));
        return "register";
    }

    @GetMapping("/admin-page")
    public String admin(Model model){
        if(session.getAttribute("admin")==null){
            return "error";
        }
        List<User> users = userService.findAllUser();
        users.removeIf(o->o.getRole().equals("admin"));
        model.addAttribute("users",users);
        return "admin";
    }

    @PostMapping("/save-user")
    public String signup(User user,Model model) {
        List<User> users = userService.findAllUser();
        
        for(User userDb : users){
            if(userDb.getUsername().equals(user.getUsername())){
                session.setAttribute("error", "Tài khoản đã tồn tại vui lòng chọn tên khác");
                return "redirect:/signup";
            }
        }
        user.setRole("user");
        userService.saveUser(user);
        session.setAttribute("msg","Đăng ký tài khoản thành công vui lòng đăng nhập ");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        if(session.getAttribute("admin")!=null){
            session.removeAttribute("admin");
        }else{
            if(session.getAttribute("user")!=null){
                session.removeAttribute("user");
            }
        }
        return "redirect:/contact";
    }

}

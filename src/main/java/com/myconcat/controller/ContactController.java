package com.myconcat.controller;

import com.myconcat.entity.Contact;
import com.myconcat.entity.User;
import com.myconcat.service.ContactService;
import com.myconcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

//    @GetMapping("/")
//    public String index(){
//        return "redirect:/contact";
//    }

    @GetMapping("/contact")
    public String list(Model model) {
        if (session.getAttribute("user")==null){
            String msg = "Phiên đăng nhập đã hết hạn vui lòng đăng nhập lại";
            session.setAttribute("msg",msg);
            session.setMaxInactiveInterval(60);
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        System.out.println("Thong tin: "+user);
        model.addAttribute("contacts", contactService.findByUserId(user.getId()));
        return "list";
    }

    @GetMapping("/contact/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/contact";
        }
        model.addAttribute("contacts", contactService.search(term));
        return "list";
    }

    @GetMapping("/contact/add")
    public String add(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }
    @PostMapping("/contact/save")
    public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        User user = (User) session.getAttribute("user");
        contact.setUser_id(user.getId());
        contactService.save(contact);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/contact";
    }
    @GetMapping("/contact/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        contactService.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
        return "redirect:/contact";
    }

}

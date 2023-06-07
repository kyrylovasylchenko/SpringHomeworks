package com.example.springhomeworks.controller;

import com.example.springhomeworks.entity.User;
import com.example.springhomeworks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registerForm(){
        return "registerForm.html";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute User user){
        userService.save(user);
        return "order.html";
    }
}

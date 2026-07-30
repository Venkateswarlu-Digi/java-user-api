package com.gmmco.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmmco.user.model.User;
import com.gmmco.user.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public User getUser(
            @RequestParam(required = false) String empId,
            @RequestParam(required = false) String email) {
        return userService.getUser(empId, email);
    }
}
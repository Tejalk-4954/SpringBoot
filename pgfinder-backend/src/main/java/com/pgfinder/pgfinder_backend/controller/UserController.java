package com.pgfinder.pgfinder_backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pgfinder.pgfinder_backend.Entity.User;
import com.pgfinder.pgfinder_backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@ModelAttribute User user, @RequestParam("aadhar") MultipartFile aadhar) throws IOException {
        return userService.register(user, aadhar);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}

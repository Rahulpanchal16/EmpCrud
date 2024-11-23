package com.emp.crud.controller;


import com.emp.crud.service.UserService;
import com.emp.crud.util.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create-user")
    public String createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
    }
}

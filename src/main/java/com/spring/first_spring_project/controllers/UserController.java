package com.spring.first_spring_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @GetMapping(path = "user")
    public String getUser(){
        return new HashMap<String,Object>().toString();
    }
}

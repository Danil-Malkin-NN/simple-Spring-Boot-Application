package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Value("${app.version.server}")
    private String name;

    @RequestMapping
    public String sayHello() {
        return name;
    }
}

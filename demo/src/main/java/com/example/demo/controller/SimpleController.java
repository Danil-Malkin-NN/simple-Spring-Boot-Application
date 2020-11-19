package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {

    @RequestMapping
    public String sayHello(){
        return "Hello world";
    }
}

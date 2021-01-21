package com.example.demo.controller;

import com.example.demo.service.ExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Exchange")
public class ExchangeProject {

    @Autowired
    ExchangeService exchangeService;

    @RequestMapping(method = RequestMethod.GET)
    public void getExchangeRate() throws JsonProcessingException {
        /*return*/
        exchangeService.getExchangeRate();
    }

}

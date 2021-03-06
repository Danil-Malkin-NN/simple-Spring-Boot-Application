package com.controller;

import com.entities.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Exchange")
public class MainController {

    @Autowired
    ExchangeService exchangeService;

    @RequestMapping(value = "currency", method = RequestMethod.GET)
    public Rate getCurrency() throws JsonProcessingException {
        return exchangeService.getExchangeRate();
    }

}

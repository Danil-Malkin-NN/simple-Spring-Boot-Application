package com.example.demo.service;

import com.example.demo.dto.Kurs;
import com.example.demo.entities.Currency;
import com.example.demo.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    CurrencyRepository currencyRepository;

    private final String URI = "http://localhost:8081/Exchange/currency";

    @PostConstruct
    public void getExchangeRate() throws JsonProcessingException {
        Runnable runnable = new Runnable() {
            public void run() {

                String str = "";
                try {
                    str = WebClient.create()
                            .get()
                            .uri(URI).retrieve()
                            .bodyToMono(String.class)
                            .block();
                } catch (WebServerException e) {
                    System.out.println(e.getClass());
                }

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Kurs kurs = new Kurs();
                try {
                    kurs = objectMapper.readValue(str, Kurs.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                List<Currency> currencyList = new ArrayList<>();
                for (String k : kurs.getStringCurrencyMap().keySet()) {
                    currencyList.add(kurs.getStringCurrencyMap().get(k));
                }

                currencyRepository.saveAll(currencyList);
            }
        };
        runnable.run();
    }

}

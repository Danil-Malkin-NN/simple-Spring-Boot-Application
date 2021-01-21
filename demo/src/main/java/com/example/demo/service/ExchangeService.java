package com.example.demo.service;

import com.example.demo.entities.Kurs;
import com.example.demo.repositories.KursRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import javax.annotation.PostConstruct;

@Service
public class ExchangeService {

    @Autowired
    KursRepository kursRepository;

    private final String URI = "http://localhost:8081/Exchange/currency";

    @PostConstruct
    public void getExchangeRate() throws JsonProcessingException {
        String str = "";
        try {
            str = WebClient.create()
                    .get()
                    .uri(URI).retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientException e) {
            System.out.println(e.getClass());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Kurs kurs = objectMapper.readValue(str, Kurs.class);

        kursRepository.save(kurs);
    }

}

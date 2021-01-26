package com.service;

import com.entities.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExchangeService {
    private final String URI = "https://www.cbr-xml-daily.ru/daily_json.js";

    public Rate getExchangeRate() throws JsonProcessingException {
        String str = "";
        ResponseEntity<String> entity = WebClient.create()
                .get()
                .uri(URI).retrieve()
                .toEntity(String.class).block();

        if (entity.getStatusCode().is2xxSuccessful()) {
            str = entity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Rate rate = objectMapper.readValue(str, Rate.class);
            return rate;
        }
        return null;

    }

}

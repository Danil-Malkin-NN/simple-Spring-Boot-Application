package com.service;

import com.entities.Kurs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExchangeService {
    private final String URI = "https://www.cbr-xml-daily.ru/daily_json.js";

    public Kurs getExchangeRate() throws JsonProcessingException {

        String str = WebClient.create()
                .get()
                .uri(URI).retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Kurs kurs = objectMapper.readValue(str, Kurs.class);

        return kurs;
    }

}

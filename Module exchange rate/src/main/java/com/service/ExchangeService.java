package com.service;

import com.entities.Kurs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class ExchangeService {
    private final String URI = "https://www.cbr-xml-daily.ru/daily_json.js";

    public Kurs getExchangeRate() throws JsonProcessingException {
        String str = "";
        try {
            str = WebClient.create()
                    .get()
                    .uri(URI).retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientException | WebServerException e) {
            System.out.println(e.getStackTrace());
            System.out.println("Имя ошибки" + e.getClass() + "!!!!");
        }

        if (!str.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Kurs kurs = objectMapper.readValue(str, Kurs.class);

            return kurs;
        } else {
            return null;
        }
    }

    public Kurs getExchangeRateRestTemplate() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(URI, String.class);

        if (!str.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Kurs kurs = objectMapper.readValue(str, Kurs.class);

            return kurs;
        } else {
            return null;
        }

    }

}

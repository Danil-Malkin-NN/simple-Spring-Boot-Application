package com.example.demo.service;

import com.example.demo.dto.Kurs;
import com.example.demo.entities.Currency;
import com.example.demo.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//        try {

//        } catch (WebClientException | WebServerException e) {
//            System.out.println(e.getStackTrace());
//        }

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        if (str != null || !str.isEmpty()) {
//            try {
//                kurs = objectMapper.readValue(str, Kurs.class);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                }
        ResponseEntity<Kurs> kurs = WebClient.create()
                .get()
                .uri(URI).retrieve()
                .toEntity(Kurs.class).block();

        if (kurs.getStatusCode().is2xxSuccessful()) {

            List<Currency> currencyList = new ArrayList<>();
            for (String k : kurs.getBody().getStringCurrencyMap().keySet()) {
                currencyList.add(kurs.getBody().getStringCurrencyMap().get(k));
            }
            currencyRepository.saveAll(currencyList);
        }
    }

}

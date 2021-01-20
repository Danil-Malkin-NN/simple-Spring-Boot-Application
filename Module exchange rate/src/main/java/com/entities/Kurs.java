package com.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Kurs {

    @JsonProperty("Valute")
    private Map<String, Currency> stringCurrencyMap = new HashMap<>();

    public Map<String, Currency> getStringCurrencyMap() {
        return stringCurrencyMap;
    }

    public void setStringCurrencyMap(Map<String, Currency> stringCurrencyMap) {
        String[] key = stringCurrencyMap.keySet().toArray(String[]::new);
        for (String s : key) {
            if (s.equals("USD") || s.equals("EUR")) {
                continue;
            }
            stringCurrencyMap.remove(s);
        }
        this.stringCurrencyMap = stringCurrencyMap;
    }
}

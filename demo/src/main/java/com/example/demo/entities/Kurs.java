package com.example.demo.entities;

import java.util.HashMap;
import java.util.Map;

public class Kurs {

    private Map<String, Currency> stringCurrencyMap = new HashMap<>();

    public Map<String, Currency> getStringCurrencyMap() {
        return stringCurrencyMap;
    }

    public void setStringCurrencyMap(Map<String, Currency> stringCurrencyMap) {
        this.stringCurrencyMap = stringCurrencyMap;
    }

}

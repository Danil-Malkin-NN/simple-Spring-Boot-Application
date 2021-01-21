package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Kurs {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonProperty("Valute")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Valute_Curs_mapping",
            joinColumns = { @JoinColumn(name = "Kurs_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "currency_id", referencedColumnName = "id") })
    @MapKey(name = "name")
    private Map<String, Currency> stringCurrencyMap = new HashMap<>();

    public Map<String, Currency> getStringCurrencyMap() {
        return stringCurrencyMap;
    }

    public void setStringCurrencyMap(Map<String, Currency> stringCurrencyMap) {
        this.stringCurrencyMap = stringCurrencyMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

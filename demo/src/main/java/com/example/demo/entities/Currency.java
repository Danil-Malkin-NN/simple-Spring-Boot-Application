package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @JsonProperty("CharCode")
    @Column(name = "name")
    private String name;

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Nominal")
    private int nominal;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}

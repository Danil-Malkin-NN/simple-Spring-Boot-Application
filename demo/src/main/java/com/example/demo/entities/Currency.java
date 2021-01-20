package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {

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
}

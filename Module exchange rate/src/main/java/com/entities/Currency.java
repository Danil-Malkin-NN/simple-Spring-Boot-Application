package com.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {

    @JsonProperty("CharCode")
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
}

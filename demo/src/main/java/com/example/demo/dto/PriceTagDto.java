package com.example.demo.dto;

import java.util.HashMap;
import java.util.Map;

public class PriceTagDto extends TagDto {

    private Integer count = 0;

    private final Map<String, Double> prices = new HashMap<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices.put("RUB", prices.doubleValue());
    }
}

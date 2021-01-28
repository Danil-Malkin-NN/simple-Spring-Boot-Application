package com.example.demo.dto;

import java.util.Objects;

public class AllTagDto extends TagDto {

    private Integer count = 0;

    private Integer price = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllTagDto allTagDto = (AllTagDto) o;
        return Objects.equals(count, allTagDto.count) &&
                Objects.equals(price, allTagDto.price) &&
                Objects.equals(getName(), allTagDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, price);
    }

    @Override
    public String toString() {
        return "AllTagDto{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", count=" + count +
                ", price=" + price +
                "} ";
    }
}

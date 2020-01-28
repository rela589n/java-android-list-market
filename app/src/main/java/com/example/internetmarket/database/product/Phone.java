package com.example.internetmarket.product;

import java.util.Calendar;

public class Phone extends Product {
    protected Integer width;
    protected Integer height;

    protected Integer battery;

    public Phone(String name, String description, Double price, Boolean inStock, Integer count, Calendar deliveryDate, ProductCategory category, Integer width, Integer height, Integer battery) {
        super(name, description, price, inStock, count, deliveryDate, category);
        this.width = width;
        this.height = height;
        this.battery = battery;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}

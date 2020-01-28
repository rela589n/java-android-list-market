package com.example.internetmarket.product;

import java.util.Calendar;

public class Laptop extends Product {
    protected String model;
    protected Integer year;

    public Laptop(String name, String description, Double price, Boolean inStock, Integer count, Calendar deliveryDate, ProductCategory category, String model, Integer year) {
        super(name, description, price, inStock, count, deliveryDate, category);
        this.model = model;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}

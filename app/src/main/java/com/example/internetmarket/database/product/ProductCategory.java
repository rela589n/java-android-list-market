package com.example.internetmarket.database.product;

public class ProductCategory {
    public ProductCategory(String name) {
        this.name = name;
    }

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

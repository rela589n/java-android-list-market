package com.example.internetmarket.database.product;

import com.example.internetmarket.database.generic.DatabaseEntity;

public class ProductCategory implements DatabaseEntity {
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

package com.example.internetmarket.database.product;

import java.io.Serializable;

public class ProductCategory implements Serializable {
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

package com.example.internetmarket.database.product;

import com.example.internetmarket.database.generic.DatabaseEntity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class Product implements DatabaseEntity {
    protected String name;
    protected String description;
    protected Double price;
    protected Boolean inStock;
    protected Integer count;
    protected Calendar deliveryDate;

    protected ProductCategory category;

    public Product(String name, String description, Double price, Boolean inStock, Integer count, Calendar deliveryDate, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
        this.count = count;
        this.deliveryDate = deliveryDate;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}

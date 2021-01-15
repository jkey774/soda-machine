package com.example.demo;

public class Product {

    private String name;

    private double price;

    private int stock;

    private long purchasedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(long purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}

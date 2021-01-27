package com.example.demo;

import com.google.cloud.firestore.FieldValue;

public final class Order {

    private final String name;

    private final double price;

    private final FieldValue timestamp;

    public Order(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.timestamp = FieldValue.serverTimestamp();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public FieldValue getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}

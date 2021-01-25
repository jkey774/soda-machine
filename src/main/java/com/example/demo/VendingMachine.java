package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class VendingMachine {

    private double currentBalance = 0.0;

    private Product[] products;

    private String orderStatus;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String getOrderStatus() { return orderStatus; }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

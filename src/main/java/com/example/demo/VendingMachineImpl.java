package com.example.demo;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VendingMachineImpl implements VendingMachine {

    private double currentBalance = 0.0;
    private List<Product> products;
    private String orderStatus;

    @Override
    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

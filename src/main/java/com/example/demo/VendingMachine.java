package com.example.demo;

import java.util.List;

public interface VendingMachine {
    double getCurrentBalance();
    void setCurrentBalance(double currentBalance);
    List<Product> getProducts();
    void setProducts(List<Product> products);
    String getOrderStatus();
    void setOrderStatus(String orderStatus);
}

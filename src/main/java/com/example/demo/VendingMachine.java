package com.example.demo;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class VendingMachine {

    private double currentBalance = 0.0;

    private List<Product> products;

    private String orderStatus;

    private final VendingMachineService service;

    public VendingMachine(VendingMachineService service) {
        this.service = service;
    }

    public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
        return service.fetchProduct(productId);
    }

    public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
        return service.fetchProducts();
    }

    public void submitOrder(Product product) throws ExecutionException, InterruptedException {
        service.submitOrder(product);
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderStatus() { return orderStatus; }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

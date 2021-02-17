package com.example.demo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface VendingMachineRepository {
    Product fetchProduct(String productId) throws ExecutionException, InterruptedException;
    List<Product> fetchProducts() throws ExecutionException, InterruptedException;
    void submitOrder(Product product) throws ExecutionException, InterruptedException;
}

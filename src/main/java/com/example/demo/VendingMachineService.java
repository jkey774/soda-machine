package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface VendingMachineService {

    Product fetchProduct(String productId) throws ExecutionException, InterruptedException;

    Product[] fetchProducts() throws ExecutionException, InterruptedException;

    void submitOrder(Product product) throws ExecutionException, InterruptedException;

}

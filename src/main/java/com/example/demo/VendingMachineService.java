package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface VendingMachineService {
    Product[] fetchProducts() throws ExecutionException, InterruptedException;
    Order submitOrder(String name) throws ExecutionException, InterruptedException;
}

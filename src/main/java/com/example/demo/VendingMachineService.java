package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface VendingMachineService {
    Product[] fetchInventorySummary() throws ExecutionException, InterruptedException;
    Product submitOrder(String productName) throws ExecutionException, InterruptedException;
}

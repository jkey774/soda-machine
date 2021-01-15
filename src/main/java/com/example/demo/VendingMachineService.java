package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface VendingMachineService {
    Product[] fetchInventorySummary();
    Product submitOrder(String productName) throws ExecutionException, InterruptedException;
}

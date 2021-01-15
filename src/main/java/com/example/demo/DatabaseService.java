package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface DatabaseService {

    Product submitOrder(String productName) throws InterruptedException, ExecutionException;
    Product[] getInventorySummary() throws InterruptedException, ExecutionException;
    Product getProduct(String id) throws InterruptedException, ExecutionException;

}

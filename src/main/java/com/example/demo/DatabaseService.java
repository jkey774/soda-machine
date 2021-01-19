package com.example.demo;

import java.util.concurrent.ExecutionException;

public interface DatabaseService {

    Product[] getProducts() throws InterruptedException, ExecutionException;
    Order submitOrder(String productId) throws InterruptedException, ExecutionException;

}

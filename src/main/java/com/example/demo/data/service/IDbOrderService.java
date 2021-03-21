package com.example.demo.data.service;

import com.example.demo.model.Product;

import java.util.concurrent.ExecutionException;

public interface IDbOrderService {

  void submitOrder(Product product) throws ExecutionException, InterruptedException;
}

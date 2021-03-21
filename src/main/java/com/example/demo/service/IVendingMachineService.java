package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IVendingMachineService {

  Product fetchProduct(String productId) throws ExecutionException, InterruptedException;

  List<Product> fetchProducts() throws ExecutionException, InterruptedException;

  void submitOrder(Product product) throws ExecutionException, InterruptedException;
}

package com.example.demo.data.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IDbProductService {

  Product fetchProduct(String productId) throws ExecutionException, InterruptedException;

  List<Product> fetchProducts() throws ExecutionException, InterruptedException;
}

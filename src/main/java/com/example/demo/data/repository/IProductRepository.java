package com.example.demo.data.repository;

import com.example.demo.model.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IProductRepository {

  Product fetchProduct(String productId) throws ExecutionException, InterruptedException;

  List<Product> fetchProducts() throws ExecutionException, InterruptedException;
}

package com.example.demo.data.repository;

import com.example.demo.model.Product;
import java.util.concurrent.ExecutionException;

public interface IOrderRepository {

  void submitOrder(Product product) throws ExecutionException, InterruptedException;
}

package com.example.demo.data.service;

import com.example.demo.data.repository.IOrderRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DbOrderService implements IDbOrderService {

  private final IOrderRepository orderRepository;

  @Autowired
  public DbOrderService(IOrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void submitOrder(Product product) throws ExecutionException, InterruptedException {
    orderRepository.submitOrder(product);
  }
}

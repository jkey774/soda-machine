package com.example.demo.data.service;

import com.example.demo.data.repository.IProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DbProductService implements IDbProductService {

  private final IProductRepository productRepository;

  @Autowired
  public DbProductService(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
    return productRepository.fetchProduct(productId);
  }

  @Override
  public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
    return productRepository.fetchProducts();
  }
}

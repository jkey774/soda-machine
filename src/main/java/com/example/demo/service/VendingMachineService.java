package com.example.demo.service;

import com.example.demo.data.service.IDbOrderService;
import com.example.demo.data.service.IDbProductService;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VendingMachineService implements IVendingMachineService {

  private final IDbProductService dbProductService;
  private final IDbOrderService dbOrderService;
  private final IPaymentService paymentService;

  @Autowired
  public VendingMachineService(
      IDbProductService dbProductService,
      IDbOrderService dbOrderService,
      IPaymentService paymentService) {
    this.dbProductService = dbProductService;
    this.dbOrderService = dbOrderService;
    this.paymentService = paymentService;
  }

  @Override
  public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
    return dbProductService.fetchProduct(productId);
  }

  @Override
  public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
    return dbProductService.fetchProducts();
  }

  @Override
  public void submitOrder(Product product) throws ExecutionException, InterruptedException {
    dbOrderService.submitOrder(product);
  }
}

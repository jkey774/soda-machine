package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
        return productRepo.read(productId);
    }

    @Override
    public Product[] fetchProducts() throws ExecutionException, InterruptedException {
        return productRepo.readAll();
    }

    @Override
    public void submitOrder(Product product) throws ExecutionException, InterruptedException {
        orderRepo.create(new Order(product));

        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("stock", product.getStock() - 1);

        productRepo.update(product, updatedFields);
    }

}

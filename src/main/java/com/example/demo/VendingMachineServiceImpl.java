package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    @Autowired
    private DatabaseServiceImpl databaseService;


    @Override
    public Product[] fetchProducts() throws ExecutionException, InterruptedException {
        return databaseService.getProducts();
    }

    @Override
    public Order submitOrder(String productId) throws ExecutionException, InterruptedException {
        return databaseService.submitOrder(productId);
    }

}

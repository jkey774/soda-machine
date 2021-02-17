package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineRepository repository;

    public VendingMachineServiceImpl(VendingMachineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
        return repository.fetchProduct(productId);
    }

    @Override
    public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
        return repository.fetchProducts();
    }

    @Override
    public void submitOrder(Product product) throws ExecutionException, InterruptedException {
        repository.submitOrder(product);
    }

}

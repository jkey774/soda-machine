package com.example.demo;

import java.util.concurrent.ExecutionException;

public class VendingMachineServiceImpl implements VendingMachineService {

    private static final DatabaseServiceImpl databaseService = new DatabaseServiceImpl();

    @Override
    public Product[] fetchInventorySummary() throws ExecutionException, InterruptedException {
        return databaseService.getInventorySummary();
    }

    @Override
    public Product submitOrder(String productName) throws ExecutionException, InterruptedException {
        return databaseService.submitOrder(productName);
    }

//    public Product getProduct(String id) {
//        Product[] inventorySummary = fetchInventorySummary();
//        Product product = null;
//        for (Product item : inventorySummary) {
//            if (StringUtils.equals(item.getName(), id)) {
//                product = item;
//                break;
//            }
//        }
//        return product;
//    }
//
//    public double getTotalProductCount() {
//        Product[] inventorySummary = fetchInventorySummary();
//        double count = 0;
//        for (Product product : inventorySummary) {
//            count += product.getStock();
//        }
//        return count;
//    }

}

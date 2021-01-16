package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ExecutionException;

public class VendingMachineServiceImpl implements VendingMachineService {

    private static final DatabaseServiceImpl vendingMachineService = new DatabaseServiceImpl();

    @Override
    public Product[] fetchInventorySummary() {
        Product[] inventorySummary = null;
        try {
            inventorySummary = vendingMachineService.getInventorySummary();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return inventorySummary;
    }

    @Override
    public Product submitOrder(String productName) throws ExecutionException, InterruptedException {
        return vendingMachineService.submitOrder(productName);
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

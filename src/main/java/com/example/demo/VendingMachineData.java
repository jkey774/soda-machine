package com.example.demo;

public class VendingMachineData {

    private double currentBalance = 0.0;

    private Product[] inventorySummary;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Product[] getInventorySummary() {
        return inventorySummary;
    }

    public void setInventorySummary(Product[] inventorySummary) {
        this.inventorySummary = inventorySummary;
    }
}

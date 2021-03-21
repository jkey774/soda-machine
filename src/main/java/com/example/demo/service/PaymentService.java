package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.model.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

  @Autowired VendingMachine vendingMachine;

  @Override
  public void deposit(Payment payment) {
    vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() + payment.getAmount());
  }

  @Override
  public void withdraw(Payment payment) {
    vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() - payment.getAmount());
  }
}

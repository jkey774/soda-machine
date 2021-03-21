package com.example.demo.service;

import com.example.demo.model.Payment;

public interface IPaymentService {
  void deposit(Payment payment);

  void withdraw(Payment payment);
}

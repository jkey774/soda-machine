package com.example.demo.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Payment {

  @Getter private final double amount;

  public Payment(double amount) {
    this.amount = amount;
  }
}

package com.example.demo.model;

import com.google.cloud.firestore.FieldValue;
import lombok.Getter;
import lombok.ToString;

@ToString
public final class Order {

  @Getter private final String productId;
  @Getter private final double price;
  @Getter private final FieldValue timestamp;

  private Order(String productId, double price, FieldValue timestamp) {
    this.productId = productId;
    this.price = price;
    this.timestamp = timestamp;
  }

  public static Order of(String productId, double price, FieldValue timestamp) {
    return new Order(productId, price, timestamp);
  }
}

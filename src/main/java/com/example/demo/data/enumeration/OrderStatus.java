package com.example.demo.data.enumeration;

import lombok.Getter;

public enum OrderStatus {
  SUCCESS("success"),
  FAILURE("failure"),
  UNKNOWN("unknown");

  @Getter private String value;

  private OrderStatus(String value) {
    this.value = value;
  }

  public static OrderStatus of(String orderStatus) {
    switch (orderStatus) {
      case "success":
        return SUCCESS;
      case "failure":
        return FAILURE;
      default:
        return UNKNOWN;
    }
  }
}

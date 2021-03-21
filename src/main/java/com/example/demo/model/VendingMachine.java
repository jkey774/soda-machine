package com.example.demo.model;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@Component
public class VendingMachine {

  private double currentBalance;
  private List<Product> products;
  private String orderStatus;
}

package com.example.demo.model;

import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product {

  private String id;
  private String name;
  private double price;
  private int stock;
}

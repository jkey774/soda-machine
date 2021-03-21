package com.example.demo.util;

import java.util.function.Function;

import com.google.cloud.firestore.DocumentSnapshot;
import com.example.demo.model.Product;

public class ProductModelTransformer {

  private ProductModelTransformer() {}

  public static final Function<DocumentSnapshot, Product> documentSnapshotToMachineProduct =
      documentSnapshot -> {
        final Product dbProduct = documentSnapshot.toObject(Product.class);

        return Product.builder()
            .id(documentSnapshot.getId())
            .name(dbProduct.getName())
            .price(dbProduct.getPrice())
            .stock(dbProduct.getStock())
            .build();
      };
}

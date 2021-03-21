package com.example.demo.data.repository;

import com.example.demo.model.Product;
import com.example.demo.util.ProductModelTransformer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.DB_PRODUCTS_COLLECTION;

@Repository
public class ProductRepository implements IProductRepository {

  private static final Firestore db = FirestoreClient.getFirestore();

  @Override
  public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
    DocumentReference productRef = db.collection(DB_PRODUCTS_COLLECTION).document(productId);
    ApiFuture<DocumentSnapshot> productFuture = productRef.get();
    DocumentSnapshot productDoc = productFuture.get();
    if (!productDoc.exists()) return null;

    return ProductModelTransformer.documentSnapshotToMachineProduct.apply(productDoc);
  }

  @Override
  public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
    ApiFuture<QuerySnapshot> productsFuture = db.collection(DB_PRODUCTS_COLLECTION).get();
    List<QueryDocumentSnapshot> productDocs = productsFuture.get().getDocuments();

    return productDocs.stream()
        .map(ProductModelTransformer.documentSnapshotToMachineProduct)
        .collect(Collectors.toList());
  }
}

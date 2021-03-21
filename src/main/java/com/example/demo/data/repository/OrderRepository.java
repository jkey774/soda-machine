package com.example.demo.data.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

import static com.example.demo.util.Constants.*;

@Repository
public class OrderRepository implements IOrderRepository {

  private static final Firestore db = FirestoreClient.getFirestore();

  @Override
  public void submitOrder(Product product) throws ExecutionException, InterruptedException {
    DocumentReference productRef = db.collection(DB_PRODUCTS_COLLECTION).document(product.getId());
    DocumentReference ordersRef = db.collection(DB_ORDERS_COLLECTION).document();

    ApiFuture<Void> orderTransaction =
        db.runTransaction(
            transaction -> {
              DocumentSnapshot productSnapshot = transaction.get(productRef).get();
              Long oldStock = productSnapshot.getLong(DB_PRODUCT_STOCK_FIELD);
              if (oldStock == null) return null;

              transaction.update(productRef, DB_PRODUCT_STOCK_FIELD, oldStock - 1);

              Order order =
                  Order.of(product.getId(), product.getPrice(), FieldValue.serverTimestamp());
              transaction.set(ordersRef, order);

              return null;
            });

    orderTransaction.get();
  }
}

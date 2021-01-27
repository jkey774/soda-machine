package com.example.demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class OrderRepository implements ObjectRepository<Order> {

    private static final String ORDERS_COLLECTION = "orders";
    private static final Firestore db = FirestoreClient.getFirestore();


    @Override
    public void create(Order order) throws ExecutionException, InterruptedException {
        DocumentReference productsRef = db.collection(ORDERS_COLLECTION).document();
        ApiFuture<WriteResult> productCreateFuture = productsRef.set(order);
        productCreateFuture.get();
    }


    @Override
    public Order read(String primaryKey) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Order[] readAll() throws InterruptedException, ExecutionException {
        return new Order[0];
    }

    @Override
    public void update(Order order, Map<String, Object> fields) throws InterruptedException, ExecutionException {

    }

    @Override
    public void delete(Order order) throws InterruptedException, ExecutionException {

    }

}

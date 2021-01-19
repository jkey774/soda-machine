package com.example.demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final String PRODUCTS_COLLECTION = "products";
    private static final String ORDERS_COLLECTION = "orders";

    @Override
    public Product[] getProducts() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> productsFuture = db.collection(PRODUCTS_COLLECTION).get();
        List<QueryDocumentSnapshot> productDocs = productsFuture.get().getDocuments();
        Product[] products = new Product[productDocs.size()];
        for (int i = 0; i < productDocs.size(); i++) {
            QueryDocumentSnapshot document = productDocs.get(i);
            Product product = document.toObject(Product.class);
            product.setId(document.getId());
            products[i] = product;
        }
        return products;
    }

    @Override
    public Order submitOrder(String productId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference productRef = db.collection(PRODUCTS_COLLECTION).document(productId);
        ApiFuture<DocumentSnapshot> productFuture = productRef.get();
        DocumentSnapshot productSnapshot = productFuture.get();

        if (!productSnapshot.exists())
            return null;

        Product product = productSnapshot.toObject(Product.class);

        if (product == null)
            return null;

        ApiFuture<WriteResult> productWriteFuture = productRef.update("stock", product.getStock() - 1);
        productWriteFuture.get();

        Order order = new Order();
        order.setName(product.getName());
        order.setPrice(product.getPrice());
        order.setTimestamp(FieldValue.serverTimestamp());

        DocumentReference ordersRef = db.collection(ORDERS_COLLECTION).document();
        ApiFuture<WriteResult> orderWriteFuture = ordersRef.set(order);
        orderWriteFuture.get();

        return order;
    }
}

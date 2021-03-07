package com.example.demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class VendingMachineRepositoryImpl implements VendingMachineRepository {

    private static final Firestore db = FirestoreClient.getFirestore();
    private static final String PRODUCTS_COLLECTION = "products";
    private static final String PRODUCT_STOCK_FIELD = "stock";
    private static final String ORDERS_COLLECTION = "orders";

    @Override
    public Product fetchProduct(String productId) throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection(PRODUCTS_COLLECTION).document(productId);
        ApiFuture<DocumentSnapshot> productFuture = productRef.get();
        DocumentSnapshot productDoc = productFuture.get();
        if (!productDoc.exists())
            return null;

        Product product = productDoc.toObject(Product.class);
        product.setId(productId);

        return product;
    }

    @Override
    public List<Product> fetchProducts() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> productsFuture = db.collection(PRODUCTS_COLLECTION).get();
        List<QueryDocumentSnapshot> productDocs = productsFuture.get().getDocuments();
        List<Product> products = new ArrayList<>(productDocs.size());
        for (QueryDocumentSnapshot productDoc : productDocs) {
            Product product = productDoc.toObject(Product.class);
            product.setId(productDoc.getId());
            products.add(product);
        }
        return products;
    }

    @Override
    public void submitOrder(Product product) throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection(PRODUCTS_COLLECTION).document(product.getId());
        DocumentReference ordersRef = db.collection(ORDERS_COLLECTION).document();

        ApiFuture<Void> orderTransaction = db.runTransaction(transaction -> {
            DocumentSnapshot productSnapshot = transaction.get(productRef).get();
            Long oldStock = productSnapshot.getLong(PRODUCT_STOCK_FIELD);
            if (oldStock == null)
                return null;

            transaction.update(productRef, PRODUCT_STOCK_FIELD, oldStock - 1);
            transaction.set(ordersRef, new Order(product));
            return null;
        });

        orderTransaction.get();
    }

}

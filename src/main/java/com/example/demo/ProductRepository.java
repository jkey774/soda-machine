package com.example.demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class ProductRepository implements ObjectRepository<Product> {

    private static final String PRODUCTS_COLLECTION = "products";
    private static final Firestore db = FirestoreClient.getFirestore();


    @Override
    public void create(Product product) throws ExecutionException, InterruptedException {
        DocumentReference productsRef = db.collection(PRODUCTS_COLLECTION).document();
        ApiFuture<WriteResult> productSaveFuture = productsRef.set(product);
        productSaveFuture.get();
    }

    @Override
    public Product read(String productId) throws ExecutionException, InterruptedException {
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
    public Product[] readAll() throws InterruptedException, ExecutionException {
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
    public void update(Product product, Map<String, Object> fields) throws InterruptedException, ExecutionException {
        DocumentReference productRef = db.collection(PRODUCTS_COLLECTION).document(product.getId());
        ApiFuture<DocumentSnapshot> productFuture = productRef.get();
        DocumentSnapshot productDoc = productFuture.get();
        if (!productDoc.exists())
            return;

        ApiFuture<WriteResult> updateFuture = productRef.update(fields);
        updateFuture.get();
    }

    @Override
    public void delete(Product product) throws InterruptedException, ExecutionException {

    }
}

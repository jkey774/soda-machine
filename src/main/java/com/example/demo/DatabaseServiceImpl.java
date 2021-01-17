package com.example.demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final String COL_ORDERS = "orders";
    private static final String COL_INVENTORY = "product_categories";

    @Override
    public Product submitOrder(String productName) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> productCategoriesFuture = db.collection(COL_INVENTORY).get();
        List<QueryDocumentSnapshot> productCategoryDocs = productCategoriesFuture.get().getDocuments();
        Product purchasedProduct = null;
        for (QueryDocumentSnapshot productCategoryDoc : productCategoryDocs) {
            if (StringUtils.equals(productCategoryDoc.getString("name"), productName)) {
                purchasedProduct = productCategoryDoc.toObject(Product.class);
                String UUID = productCategoryDoc.getId();
                Double stock = productCategoryDoc.getDouble("stock");
                DocumentReference productCategoryReference = db.collection(COL_INVENTORY).document(UUID);
                productCategoryReference.update("stock", stock - 1.0);
                break;
            }
        }

        if (purchasedProduct != null) {
            purchasedProduct.setPurchasedAt(Instant.now().toEpochMilli());
            ApiFuture<WriteResult> salesRecordFuture = db.collection(COL_ORDERS).document().set(purchasedProduct);
            salesRecordFuture.get();
        }

        return purchasedProduct;
    }

    @Override
    public Product[] getInventorySummary() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> productsFuture = db.collection(COL_INVENTORY).get();
        List<QueryDocumentSnapshot> productDocs = productsFuture.get().getDocuments();
        Product[] products = new Product[productDocs.size()];
        for (int i = 0; i < productDocs.size(); i++) {
            QueryDocumentSnapshot document = productDocs.get(i);
            Product product = document.toObject(Product.class);
            products[i] = product;
        }
        return products;
    }

//    public List<Product> getFullInventory() throws InterruptedException, ExecutionException {
//
//    }
    
    @Override
    public Product getProduct(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docReference = db.collection(COL_INVENTORY).document(id);
        ApiFuture<DocumentSnapshot> future = docReference.get();
        DocumentSnapshot document = future.get();
        return document.toObject(Product.class);
    }

}

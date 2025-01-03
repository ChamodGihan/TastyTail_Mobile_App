package com.example.dogfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dogfood.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public ProductDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUCT_NAME, product.getName());
        values.put(DatabaseHelper.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(DatabaseHelper.COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(DatabaseHelper.COLUMN_PRODUCT_IMAGE_URL, product.getImgUrl());
        return db.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PRODUCTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_IMAGE_URL))
                );
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        Cursor cursor = null;

        try {
            cursor = db.query(
                    DatabaseHelper.TABLE_PRODUCTS,  // The table to query
                    new String[]{
                            DatabaseHelper.COLUMN_PRODUCT_ID,
                            DatabaseHelper.COLUMN_PRODUCT_NAME,
                            DatabaseHelper.COLUMN_PRODUCT_DESCRIPTION,
                            DatabaseHelper.COLUMN_PRODUCT_PRICE,
                            DatabaseHelper.COLUMN_PRODUCT_IMAGE_URL
                    },  // The columns to return
                    DatabaseHelper.COLUMN_PRODUCT_ID + "=?",  // The columns for the WHERE clause
                    new String[]{String.valueOf(productId)},  // The values for the WHERE clause
                    null,  // Group the rows
                    null,  // Filter by row groups
                    null   // The sort order
            );

            if (cursor != null && cursor.moveToFirst()) {
                // Create a new Product object with the retrieved data
                product = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_IMAGE_URL))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure the cursor is closed
            }
            // No need to close the db connection here; it is managed by ProductDAO
        }

        return product;
    }

    public String getProductNameById(int productId) {
        Cursor cursor = null;
        String productName = null;

        try {
            cursor = db.query(
                    DatabaseHelper.TABLE_PRODUCTS,
                    new String[]{DatabaseHelper.COLUMN_PRODUCT_NAME},
                    DatabaseHelper.COLUMN_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(productId)},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                productName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_NAME));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure the cursor is closed
            }
        }

        return productName;
    }

    public double getProductPriceById(int productId) {
        Cursor cursor = null;
        double productPrice = 0.0;

        try {
            cursor = db.query(
                    DatabaseHelper.TABLE_PRODUCTS,
                    new String[]{DatabaseHelper.COLUMN_PRODUCT_PRICE},
                    DatabaseHelper.COLUMN_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(productId)},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                productPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_PRICE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure the cursor is closed
            }
        }

        return productPrice;
    }
}

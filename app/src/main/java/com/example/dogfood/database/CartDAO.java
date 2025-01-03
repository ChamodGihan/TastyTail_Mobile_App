package com.example.dogfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.dogfood.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private SQLiteDatabase db;

    public CartDAO(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public long addCartItem(CartItem item) {
        ContentValues values = new ContentValues();
        values.put("product_id", item.getProductId());
        values.put("quantity", item.getQuantity());
        return db.insert("cart", null, values);
    }

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        Cursor cursor = db.query("cart", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                CartItem item = new CartItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity"))
                );
                cartItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        List<CartItem> cartItems = getCartItems();
        for (CartItem item : cartItems) {
            // Assuming getProductPriceById is a method that retrieves the product price from the product table
            double price = getProductPriceById(item.getProductId());
            totalPrice += price * item.getQuantity();
        }
        return totalPrice;
    }

    private double getProductPriceById(int productId) {
        Cursor cursor = db.query("product", new String[]{"price"}, "id = ?", new String[]{String.valueOf(productId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
        }
        return 0.0;
    }
}

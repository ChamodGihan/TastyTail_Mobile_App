package com.example.dogfood.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dogfood.R;
import com.example.dogfood.adapters.CartAdapter;
import com.example.dogfood.database.CartDAO;
import com.example.dogfood.models.CartItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    ListView listViewCart;
    TextView textViewTotalPrice;
    CartDAO cartDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listViewCart = findViewById(R.id.listViewCart);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        cartDAO = new CartDAO(this);

        List<CartItem> cartItems = cartDAO.getCartItems();
        CartAdapter adapter = new CartAdapter(this, cartItems);
        listViewCart.setAdapter(adapter);

        double totalPrice = cartDAO.getTotalPrice();
        textViewTotalPrice.setText("Total: $" + totalPrice);
    }
}

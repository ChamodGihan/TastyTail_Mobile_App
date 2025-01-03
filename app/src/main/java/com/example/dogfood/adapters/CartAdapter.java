package com.example.dogfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dogfood.R;
import com.example.dogfood.models.CartItem;
import com.example.dogfood.database.ProductDAO;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private ProductDAO productDAO;

    public CartAdapter(Context context, List<CartItem> items) {
        super(context, 0, items);
        productDAO = new ProductDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cart, parent, false);
        }

        CartItem item = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
        TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);

        // Assuming ProductDAO has a method to get product details by ID
        String productName = productDAO.getProductNameById(item.getProductId());
        double productPrice = productDAO.getProductPriceById(item.getProductId());

        textViewName.setText(productName);
        textViewQuantity.setText("Quantity: " + item.getQuantity());
        textViewPrice.setText("Price: $" + (productPrice * item.getQuantity()));

        return convertView;
    }
}

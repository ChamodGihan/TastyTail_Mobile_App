package com.example.dogfood.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dogfood.R;
import com.example.dogfood.database.ProductDAO;
import com.example.dogfood.models.Product;
import com.squareup.picasso.Picasso;


import java.io.ObjectInputStream;

public class ProductDetailActivity extends AppCompatActivity {
    TextView textViewName, textViewDescription, textViewPrice;
    ImageView imageViewProduct;
    ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPrice = findViewById(R.id.textViewPrice);
        imageViewProduct = findViewById(R.id.imageViewProduct);

        productDAO = new ProductDAO(this);

        int productId = getIntent().getIntExtra("productId", -1);
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            textViewName.setText(product.getName());
            textViewDescription.setText(product.getDescription());
            textViewPrice.setText("$" + product.getPrice());
            Picasso.get().load(product.getImgUrl()).into(imageViewProduct);
        }
    }
}

package com.example.dogfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dogfood.R;
import com.example.dogfood.database.UserDAO;
import com.example.dogfood.models.User;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewName, textViewEmail;
    Button buttonLogout;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);
        userDAO = new UserDAO(this);

        int userId = getIntent().getIntExtra("userId", -1);
        User user = userDAO.getUserById(userId);

        if (user != null) {
            textViewName.setText(user.getName());
            textViewEmail.setText(user.getEmail());
        }

        buttonLogout.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}

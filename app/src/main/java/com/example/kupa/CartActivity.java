package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("intValue", 0);
        int intValue1 = intent.getIntExtra("intValue1", 0);

        TextView textView = findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.textView1);

        textView.setText(String.valueOf(intValue));
        textView1.setText(String.valueOf(intValue1));
    }
}
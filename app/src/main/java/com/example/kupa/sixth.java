package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class sixth extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sixth);

        LinearLayout email=findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sixth.this, fifth.class);
                startActivity(intent);
            }
        });
        LinearLayout phone=findViewById(R.id.phone);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sixth.this, eleventh.class);
                startActivity(intent);
            }
        });

        ImageView backk=findViewById(R.id.backk);
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sixth.this, third.class);
                startActivity(intent);
            }
        });

    }
}
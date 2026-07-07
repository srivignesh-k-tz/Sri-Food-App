package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        new Handler().postDelayed(() -> {

            FirebaseUser user = auth.getCurrentUser();

            if (user != null) {
                Intent i = new Intent(MainActivity.this, New.class);
                i.putExtra("openFragment", "home");
                startActivity(i);
            } else {
                Intent i = new Intent(MainActivity.this, first.class);
                startActivity(i);
            }

            finish();

        }, 2000);
    }
}
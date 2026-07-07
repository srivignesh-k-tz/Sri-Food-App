package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Arrays;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import com.google.firebase.auth.FirebaseAuth;

public class first extends AppCompatActivity {

    TextView continu, signin1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔥 Auto Login Check (IMPORTANT)
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // User already logged in → go directly to home
            startActivity(new Intent(first.this, get_start.class));
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        // 🔹 Initialize Views
        continu = findViewById(R.id.continu);
        signin1 = findViewById(R.id.signin1);

        // 🔹 Continue Button → Next screen
        continu.setOnClickListener(v -> {
            Intent intent = new Intent(first.this, second.class);
            startActivity(intent);
        });

        // 🔹 Sign In Button → Login screen
        signin1.setOnClickListener(v -> {
            Intent intent = new Intent(first.this, signing.class);
            startActivity(intent);
        });

        // 🔹 ViewPager (Slider)
        ViewPager viewPager = findViewById(R.id.viewPager);
        CircleIndicator indicator = findViewById(R.id.indicator);

        List<Integer> imageList = Arrays.asList(
                R.drawable.orderr,
                R.drawable.deliveryy
        );

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

}
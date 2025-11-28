package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.Arrays;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class first extends AppCompatActivity {

    TextView continu;
    TextView signin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        continu =findViewById(R.id.continu);
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(first.this, second.class);
                startActivity(intent);
            }
        });

        signin1=findViewById(R.id.signin1);
        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(first.this, third.class);
                startActivity(intent);
            }
        });

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
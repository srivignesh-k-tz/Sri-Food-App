package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import java.util.Arrays;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        TextView getstarted=findViewById(R.id.getstarted);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(second.this, fourth.class);
                startActivity(intent);
            }
        });
        TextView signin2=findViewById(R.id.signin2);
        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(second.this, third.class);
                startActivity(intent);
            }
        });

                ViewPager viewPager = findViewById(R.id.viewPager);
                CircleIndicator indicator = findViewById(R.id.indicator);

                List<Integer> imageList = Arrays.asList(
                        R.drawable.deliveryy,
                        R.drawable.orderr
                );

                ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageList);
                viewPager.setAdapter(adapter);
                indicator.setViewPager(viewPager);
            }
        }

package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.kupa.bottomsheet.MenuSheet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class New extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.navigation_menu) {
                    selectedFragment = new MenuFragment();
                } else if (itemId == R.id.navigation_mycart) {
                    selectedFragment = new MycartFragment();
                } else if (itemId == R.id.navigation_profile) {
                    selectedFragment = new ProfileFragment();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
        String fragment = getIntent().getStringExtra("openFragment");

        if (fragment != null && fragment.equals("profile")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();
        }
        else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        }


//        ImageView pizza = findViewById(R.id.pizza);
//        pizza.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MenuSheet bottomSheet = new MenuSheet();
//                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
//            }
//        });

//        ImageView location = findViewById(R.id.location);
//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(New.this, location.class);
//                startActivity(intent);
//            }
//        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
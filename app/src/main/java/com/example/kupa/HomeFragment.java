package com.example.kupa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView put_street, put_city, put_pincode;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = requireContext().getSharedPreferences("Edit Address", Context.MODE_PRIVATE);

        put_street = view.findViewById(R.id.put_street);
        put_city = view.findViewById(R.id.put_city);
        put_pincode = view.findViewById(R.id.put_pincode);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        String street = sharedPreferences.getString("street", "Post, Street");
        String city = sharedPreferences.getString("city", "Taluk, District");
        String pincode = sharedPreferences.getString("pincode", "Pincode");

        if (put_street != null) {
            put_street.setText(street);
        }
        if (put_city != null) {
            put_city.setText(city);
        }
        if (put_pincode != null) {
            put_pincode.setText(pincode);
        }
    }
}

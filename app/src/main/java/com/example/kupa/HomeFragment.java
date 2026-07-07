package com.example.kupa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView put_street, put_city, put_pincode;
    private SharedPreferences sharedPreferences;

    private EditText searchHome;
    private View pizzaContainer, burgerContainer, frenchContainer, sandwichContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = requireContext().getSharedPreferences("Edit Address", Context.MODE_PRIVATE);

        put_street = view.findViewById(R.id.put_street);
        put_city = view.findViewById(R.id.put_city);
        put_pincode = view.findViewById(R.id.put_pincode);

        searchHome = view.findViewById(R.id.search_home);
        pizzaContainer = view.findViewById(R.id.home_pizza_container);
        burgerContainer = view.findViewById(R.id.home_burger_container);
        frenchContainer = view.findViewById(R.id.home_french_container);
        sandwichContainer = view.findViewById(R.id.home_sandwich_container);

        if (searchHome != null) {
            searchHome.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterItems(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        return view;
    }

    private void filterItems(String query) {
        String cleanQuery = query.toLowerCase().trim();

        boolean showPizza = cleanQuery.isEmpty() || "pizza".contains(cleanQuery);
        boolean showBurger = cleanQuery.isEmpty() || "burger".contains(cleanQuery);
        boolean showFrench = cleanQuery.isEmpty() || "french fries".contains(cleanQuery) || "french".contains(cleanQuery) || "fries".contains(cleanQuery);
        boolean showSandwich = cleanQuery.isEmpty() || "sandwich".contains(cleanQuery);

        if (pizzaContainer != null) pizzaContainer.setVisibility(showPizza ? View.VISIBLE : View.GONE);
        if (burgerContainer != null) burgerContainer.setVisibility(showBurger ? View.VISIBLE : View.GONE);
        if (frenchContainer != null) frenchContainer.setVisibility(showFrench ? View.VISIBLE : View.GONE);
        if (sandwichContainer != null) sandwichContainer.setVisibility(showSandwich ? View.VISIBLE : View.GONE);

        View[] containers = {pizzaContainer, burgerContainer, frenchContainer, sandwichContainer};
        boolean isFirst = true;
        for (View itemContainer : containers) {
            if (itemContainer != null && itemContainer.getVisibility() == View.VISIBLE) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) itemContainer.getLayoutParams();
                if (params != null) {
                    params.leftMargin = isFirst ? 0 : dpToPx(16);
                    itemContainer.setLayoutParams(params);
                }
                isFirst = false;
            }
        }
    }

    private int dpToPx(int dp) {
        if (getContext() == null) return dp;
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
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

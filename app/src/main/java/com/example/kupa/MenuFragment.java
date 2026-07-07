package com.example.kupa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.kupa.bottomsheet.BurgerSheet;
import com.example.kupa.bottomsheet.ChickenSheet;
import com.example.kupa.bottomsheet.FrenchSheet;
import com.example.kupa.bottomsheet.MenuSheet;
import com.example.kupa.bottomsheet.NoodlesSheet;
import com.example.kupa.bottomsheet.SandwichSheet;
import com.example.kupa.databinding.SheetBurgerBinding;


public class MenuFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);


        ImageView pizzaImg =view.findViewById(R.id.pizza);
        pizzaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
        ImageView burgerImg =view.findViewById(R.id.burger);
        burgerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheets();
            }
        });
        ImageView frenchImg =view.findViewById(R.id.frenchimage);
        frenchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetss();
            }
        });
        ImageView chickenImg =view.findViewById(R.id.chicken);
        chickenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottom();
            }
        });
        ImageView noodlesImg =view.findViewById(R.id.noodles);
        noodlesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottoms();
            }
        });
        ImageView sandwichImg =view.findViewById(R.id.sandwich);
        sandwichImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomss();
            }
        });

        EditText searchMenu = view.findViewById(R.id.search_menu);
        View row1 = view.findViewById(R.id.row_1);
        View row2 = view.findViewById(R.id.row_2);
        View row3 = view.findViewById(R.id.row_3);

        View pizzaContainer = view.findViewById(R.id.container_pizza);
        View burgerContainer = view.findViewById(R.id.container_burger);
        View frenchContainer = view.findViewById(R.id.container_french);
        View chickenContainer = view.findViewById(R.id.container_chicken);
        View noodlesContainer = view.findViewById(R.id.container_noodles);
        View sandwichContainer = view.findViewById(R.id.container_sandwich);

        if (searchMenu != null) {
            searchMenu.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String cleanQuery = s.toString().toLowerCase().trim();

                    boolean showPizza = cleanQuery.isEmpty() || "pizza".contains(cleanQuery);
                    boolean showBurger = cleanQuery.isEmpty() || "burger".contains(cleanQuery);
                    boolean showFrench = cleanQuery.isEmpty() || "french fries".contains(cleanQuery) || "french".contains(cleanQuery) || "fries".contains(cleanQuery);
                    boolean showChicken = cleanQuery.isEmpty() || "chicken lollipop".contains(cleanQuery) || "chicken".contains(cleanQuery) || "lollipop".contains(cleanQuery);
                    boolean showNoodles = cleanQuery.isEmpty() || "chicken noodles".contains(cleanQuery) || "noodles".contains(cleanQuery);
                    boolean showSandwich = cleanQuery.isEmpty() || "sandwich".contains(cleanQuery);

                    if (pizzaContainer != null) pizzaContainer.setVisibility(showPizza ? View.VISIBLE : View.GONE);
                    if (burgerContainer != null) burgerContainer.setVisibility(showBurger ? View.VISIBLE : View.GONE);
                    if (frenchContainer != null) frenchContainer.setVisibility(showFrench ? View.VISIBLE : View.GONE);
                    if (chickenContainer != null) chickenContainer.setVisibility(showChicken ? View.VISIBLE : View.GONE);
                    if (noodlesContainer != null) noodlesContainer.setVisibility(showNoodles ? View.VISIBLE : View.GONE);
                    if (sandwichContainer != null) sandwichContainer.setVisibility(showSandwich ? View.VISIBLE : View.GONE);

                    if (burgerContainer != null) {
                        ViewGroup.MarginLayoutParams burgerParams = (ViewGroup.MarginLayoutParams) burgerContainer.getLayoutParams();
                        if (burgerParams != null) {
                            burgerParams.leftMargin = showPizza ? dpToPx(20) : 0;
                            burgerContainer.setLayoutParams(burgerParams);
                        }
                    }
                    if (chickenContainer != null) {
                        ViewGroup.MarginLayoutParams chickenParams = (ViewGroup.MarginLayoutParams) chickenContainer.getLayoutParams();
                        if (chickenParams != null) {
                            chickenParams.leftMargin = showFrench ? dpToPx(20) : 0;
                            chickenContainer.setLayoutParams(chickenParams);
                        }
                    }
                    if (sandwichContainer != null) {
                        ViewGroup.MarginLayoutParams sandwichParams = (ViewGroup.MarginLayoutParams) sandwichContainer.getLayoutParams();
                        if (sandwichParams != null) {
                            sandwichParams.leftMargin = showNoodles ? dpToPx(20) : 0;
                            sandwichContainer.setLayoutParams(sandwichParams);
                        }
                    }

                    if (row1 != null) {
                        row1.setVisibility((showPizza || showBurger) ? View.VISIBLE : View.GONE);
                    }
                    if (row2 != null) {
                        row2.setVisibility((showFrench || showChicken) ? View.VISIBLE : View.GONE);
                    }
                    if (row3 != null) {
                        row3.setVisibility((showNoodles || showSandwich) ? View.VISIBLE : View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        return view;
    }

    private int dpToPx(int dp) {
        if (getContext() == null) return dp;
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }


    private void showBottomSheet() {
        FragmentManager fragmentManager = getChildFragmentManager();
        MenuSheet bottomSheet = new MenuSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");
    }

    private void showBottomSheets() {
        FragmentManager fragmentManager = getChildFragmentManager();
        BurgerSheet bottomSheet = new BurgerSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");

    }
    private void showBottomSheetss() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FrenchSheet bottomSheet = new FrenchSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");

    }
    private void showBottom() {
        FragmentManager fragmentManager = getChildFragmentManager();
        ChickenSheet bottomSheet = new ChickenSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");

    }
    private void showBottoms() {
        FragmentManager fragmentManager = getChildFragmentManager();
        NoodlesSheet bottomSheet = new NoodlesSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");

    }
    private void showBottomss() {
        FragmentManager fragmentManager = getChildFragmentManager();
        SandwichSheet bottomSheet = new SandwichSheet();
        bottomSheet.show(fragmentManager, "MyBottomSheetDialogFragment");

    }
}

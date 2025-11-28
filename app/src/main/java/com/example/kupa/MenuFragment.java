package com.example.kupa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return view;
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

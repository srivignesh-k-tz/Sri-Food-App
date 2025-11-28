package com.example.kupa.bottomsheet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.kupa.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private View view;
    private TextView confirm, change, set_street, set_city, set_pincode;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        set_street = view.findViewById(R.id.set_street);
        set_city = view.findViewById(R.id.set_city);
        set_pincode = view.findViewById(R.id.set_pincode);

        confirm = view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Confirmed", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        change = view.findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditAddress bottomSheet = new EditAddress();
                bottomSheet.show(getChildFragmentManager(), "ModalBottomSheet");
            }
        });

        sharedPreferences = requireContext().getSharedPreferences("Edit Address", Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Fetch the data from shared preferences and update UI
        String street = sharedPreferences.getString("street", "Post, Street");
        String city = sharedPreferences.getString("city", "Taluk, District");
        String pincode = sharedPreferences.getString("pincode", "Pincode");

        set_street.setText(street);
        set_city.setText(city);
        set_pincode.setText(pincode);
    }
}

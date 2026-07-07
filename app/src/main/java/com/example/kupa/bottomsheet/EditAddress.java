package com.example.kupa.bottomsheet;

import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.example.kupa.EditActivity;
import com.example.kupa.New;
import com.example.kupa.R;
import com.example.kupa.location;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EditAddress extends BottomSheetDialogFragment {

    private View view;
    private EditText editCity, editStreet, editPincode;
    private TextView saveAddress;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.edit_address, container, false);

        if (getActivity() != null) {
            sharedPreferences = getActivity().getSharedPreferences("Edit Address", MODE_PRIVATE);
        }

        editCity = view.findViewById(R.id.edit_city);
        editStreet = view.findViewById(R.id.edit_street);
        editPincode = view.findViewById(R.id.edit_pincode);
        saveAddress = view.findViewById(R.id.save_address);

        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editCity.getText().toString();
                String street = editStreet.getText().toString();
                String pincode = editPincode.getText().toString();

                if (city.isEmpty() || street.isEmpty() || pincode.isEmpty()) {
                    Toast.makeText(getContext(), "Fill all the parameters", Toast.LENGTH_SHORT).show();
                } else if (pincode.length() != 6) {
                    Toast.makeText(getContext(), "Pincode must be 6 digits", Toast.LENGTH_SHORT).show();
                } else {
                    // Navigate to the previous bottom sheet (assuming it is another BottomSheetDialogFragment)
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("street", editStreet.getText().toString());
                    editor.apply();

                    editor.putString("city", editCity.getText().toString());
                    editor.apply();

                    editor.putString("pincode", editPincode.getText().toString());
                    editor.apply();

                    Intent intent =
                            new Intent(getActivity(), location.class);

                    startActivity(intent);

                    dismiss(); // close bottom sheet

                    Toast.makeText(getContext(), "Address Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}

package com.example.kupa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    ImageView edit, image;
    RelativeLayout location, logout;
    TextView edit_name, edit_email;
    SharedPreferences sharedPreferences;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        image = view.findViewById(R.id.image);

        // Retrieve image URI from SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("My Profile", Context.MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("imageUri", null);
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(this)
                    .load(imageUri)
                    .into(image);
        }

        edit_name = view.findViewById(R.id.edit_name);
        edit_email = view.findViewById(R.id.edit_email);
        edit = view.findViewById(R.id.edit);
        location = view.findViewById(R.id.location);
        logout = view.findViewById(R.id.logout);

        edit.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        });

        location.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), location.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), first.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        String name = sharedPreferences.getString("name", "Username");
        String email = sharedPreferences.getString("email", "E-mail");

        edit_name.setText(name);
        edit_email.setText(email);
    }
}

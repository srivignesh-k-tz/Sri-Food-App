package com.example.kupa;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MycartFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    public static List<ModelClass> userList = new ArrayList<>();
    Adapter adapter;
    private TextView put_street, put_city, put_pincode;
    private SharedPreferences sharedPreferences;

    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycart, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        ImageView emptyImageView=view.findViewById(R.id.emptyImageView);
        TextView emptyTextView=view.findViewById(R.id.emptyTextView);

        if (userList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyImageView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyImageView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.GONE);
        initRecyclerView();
        }
        TextView showDialogButton = view.findViewById(R.id.show_dialog_button);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForThreeSeconds(getContext());
            }
        });

        sharedPreferences = requireContext().getSharedPreferences("Edit Address", Context.MODE_PRIVATE);

        put_street = view.findViewById(R.id.put_street);
        put_city = view.findViewById(R.id.put_city);
        put_pincode = view.findViewById(R.id.put_pincode);

        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showDialogForThreeSeconds(Context context) {
        if (userList.isEmpty()) {
            if (context == null) return;

            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_customs, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, 2000);
        } else {
            if (context == null) return;

            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_custom, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, 2000);
        }
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
package com.example.kupa;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {

    private EditText editTextPassword, email, name, ph;
    private ImageView imageViewTogglePassword;
    private boolean isPasswordVisible = false;
    private ImageView back, imageView;
    private TextView namesave;
    private SharedPreferences sharedPreferences;
    private Uri selectedImageUri;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);

        imageView = findViewById(R.id.image_view);
        ImageView pickImageButton = findViewById(R.id.image_view);

        pickImageButton.setOnClickListener(v -> openGallery());

        back = findViewById(R.id.backss);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(EditActivity.this, New.class);
            intent.putExtra("openFragment", "profile");
            startActivity(intent);
            finish();
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        ph = findViewById(R.id.ph);

        sharedPreferences = getSharedPreferences("My Profile", MODE_PRIVATE);

        namesave = findViewById(R.id.namesave);
        namesave.setOnClickListener(v -> {
            String textmail = email.getText().toString();
            String textpassword = editTextPassword.getText().toString();
            String textnum = ph.getText().toString();
            String textname = name.getText().toString();

            if (textmail.isEmpty() || textpassword.isEmpty() || textnum.isEmpty() || textname.isEmpty()) {
                Toast.makeText(EditActivity.this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
            } else if (textnum.length() != 10) {
                Toast.makeText(EditActivity.this, "Phone Number must be 10 digits", Toast.LENGTH_SHORT).show();
            } else if (textpassword.length() != 8) {
                Toast.makeText(EditActivity.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("email", email.getText().toString());
                if (selectedImageUri != null) {
                    editor.putString("imageUri", selectedImageUri.toString());
                }
                editor.apply();
                Intent intent = new Intent(EditActivity.this, New.class);
                intent.putExtra("openFragment", "profile");
                startActivity(intent);
                finish();
                Toast.makeText(EditActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        editTextPassword = findViewById(R.id.editTextPassword);
        imageViewTogglePassword = findViewById(R.id.imageViewTogglePassword);

        imageViewTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imageViewTogglePassword.setImageResource(R.drawable.hide);
            } else {
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imageViewTogglePassword.setImageResource(R.drawable.unhide);
            }
            isPasswordVisible = !isPasswordVisible;
            editTextPassword.setSelection(editTextPassword.getText().length());
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
    }
}

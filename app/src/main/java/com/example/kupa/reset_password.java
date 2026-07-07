package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class reset_password extends AppCompatActivity {

    private EditText editTextPassword;
    private EditText confirm_password;

    private ImageView imageViewTogglePassword;
    private ImageView conpass_eye;

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();

        // Get email from previous activity
        String email = getIntent().getStringExtra("email");

        // Views
        ImageView backs = findViewById(R.id.backs);

        editTextPassword = findViewById(R.id.editTextPass);

        confirm_password = findViewById(R.id.confirm_password);

        imageViewTogglePassword =
                findViewById(R.id.imageViewTogglePass);

        conpass_eye =
                findViewById(R.id.conpass_eye);

        TextView conti = findViewById(R.id.conti);

        // Back button
        backs.setOnClickListener(v -> finish());

        // Continue button
        conti.setOnClickListener(v -> {

            String textpass =
                    editTextPassword.getText().toString().trim();

            String textconpass =
                    confirm_password.getText().toString().trim();

            // Empty check
            if (textpass.isEmpty() || textconpass.isEmpty()) {

                Toast.makeText(
                        reset_password.this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            // Password length
            if (textpass.length() < 8) {

                Toast.makeText(
                        reset_password.this,
                        "Password must be minimum 8 characters",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            // Password match
            if (!textpass.equals(textconpass)) {

                Toast.makeText(
                        reset_password.this,
                        "Passwords are not same",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            // Send reset email
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(
                                    reset_password.this,
                                    "Password Reset Email Sent",
                                    Toast.LENGTH_LONG).show();

                            Intent intent =
                                    new Intent(
                                            reset_password.this,
                                            signing.class);

                            startActivity(intent);

                            finish();

                        } else {

                            Toast.makeText(
                                    reset_password.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Main password show/hide
        imageViewTogglePassword.setOnClickListener(v -> {

            if (isPasswordVisible) {

                editTextPassword.setTransformationMethod(
                        PasswordTransformationMethod.getInstance());

                imageViewTogglePassword
                        .setImageResource(R.drawable.hide);

            } else {

                editTextPassword.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());

                imageViewTogglePassword
                        .setImageResource(R.drawable.unhide);
            }

            isPasswordVisible = !isPasswordVisible;

            editTextPassword.setSelection(
                    editTextPassword.getText().length());
        });

        // Confirm password show/hide
        conpass_eye.setOnClickListener(v -> {

            if (isConfirmPasswordVisible) {

                confirm_password.setTransformationMethod(
                        PasswordTransformationMethod.getInstance());

                conpass_eye
                        .setImageResource(R.drawable.hide);

            } else {

                confirm_password.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());

                conpass_eye
                        .setImageResource(R.drawable.unhide);
            }

            isConfirmPasswordVisible =
                    !isConfirmPasswordVisible;

            confirm_password.setSelection(
                    confirm_password.getText().length());
        });
    }
}
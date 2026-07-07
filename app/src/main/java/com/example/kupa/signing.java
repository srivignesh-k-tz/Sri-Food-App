package com.example.kupa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signing extends AppCompatActivity {

    EditText edit_email, editTextPassword, edit_name;

    ImageView imageViewTogglePassword;
    TextView forgotpassword;

    boolean isPasswordVisible = false;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_signing);

        auth = FirebaseAuth.getInstance();

        // XML IDS
        forgotpassword=findViewById(R.id.forgotpassword);
        edit_email = findViewById(R.id.edit_email);
        edit_name = findViewById(R.id.edit_name);
        editTextPassword = findViewById(R.id.editTextPassword);
        imageViewTogglePassword = findViewById(R.id.imageViewTogglePassword);

        TextView conti = findViewById(R.id.conti);
        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(v -> finish());

        TextView signup1 = findViewById(R.id.signup1);

        // Signup page
        signup1.setOnClickListener(v ->
                startActivity(new Intent(this, signup.class)));
        forgotpassword.setOnClickListener(v -> {

            String emailText = edit_email.getText().toString().trim();

            if (emailText.isEmpty()) {
                Toast.makeText(signing.this,
                        "Enter email first",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(signing.this, forget_password.class);
            intent.putExtra("email", emailText);
            startActivity(intent);
        });

        // LOGIN BUTTON
        conti.setOnClickListener(v -> {

            String email = edit_email.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String name = edit_name.getText().toString().trim(); // ✅ FIXED

            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {

                Toast.makeText(signing.this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            ProgressDialog progressDialog =
                    new ProgressDialog(signing.this);

            progressDialog.setMessage("Logging in...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {

                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            FirebaseUser user =
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser();

                            // SHARED PREFERENCES
                            SharedPreferences sp =
                                    getSharedPreferences(
                                            "My Profile",
                                            MODE_PRIVATE);

                            SharedPreferences.Editor editor =
                                    sp.edit();

                            // SAVE EMAIL
                            if (user != null) {
                                editor.putString("email", user.getEmail());
                            }

                            // SAVE NAME FROM INPUT
                            editor.putString("name", name); // ✅ FIXED
                            editor.apply();

                            Toast.makeText(
                                    signing.this,
                                    "Login Success",
                                    Toast.LENGTH_SHORT
                            ).show();

                            Intent intent =
                                    new Intent(
                                            signing.this,
                                            get_start.class);

                            startActivity(intent);

                            finish();

                        } else {

                            Toast.makeText(
                                    signing.this,
                                    "Login Failed : "
                                            + task.getException()
                                            .getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });

        // SHOW / HIDE PASSWORD
        imageViewTogglePassword.setOnClickListener(v -> {

            if (isPasswordVisible) {

                editTextPassword.setTransformationMethod(
                        PasswordTransformationMethod
                                .getInstance());

            } else {

                editTextPassword.setTransformationMethod(
                        HideReturnsTransformationMethod
                                .getInstance());
            }

            isPasswordVisible = !isPasswordVisible;

            editTextPassword.setSelection(
                    editTextPassword.getText().length());
        });
    }
}
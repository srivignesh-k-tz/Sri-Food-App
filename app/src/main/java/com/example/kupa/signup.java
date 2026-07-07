package com.example.kupa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText edit_name, edit_mail, edit_num, editTextPassword;
    ImageView imageViewTogglePassword;
    boolean isPasswordVisible = false;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        edit_name = findViewById(R.id.edit_name);
        edit_mail = findViewById(R.id.edit_mail);
        edit_num = findViewById(R.id.edit_num);
        editTextPassword = findViewById(R.id.editTextPassword);
        imageViewTogglePassword = findViewById(R.id.imageViewTogglePassword);

        TextView register = findViewById(R.id.register);
        TextView signin3 = findViewById(R.id.signin3);
        ImageView back = findViewById(R.id.ba);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signin3.setOnClickListener(v ->
                startActivity(new Intent(this, signing.class)));

        register.setOnClickListener(v -> {

            String name = edit_name.getText().toString().trim();
            String email = edit_mail.getText().toString().trim();
            String phone = edit_num.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // show quick loading
            ProgressDialog progressDialog = new ProgressDialog(signup.this);
            progressDialog.setMessage("Registering...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            String userId = auth.getCurrentUser().getUid();

                            DatabaseReference db = FirebaseDatabase.getInstance()
                                    .getReference("Users");

                            // save data (non-blocking)
                            db.child(userId)
                                    .setValue(new UserModel(name, email, phone));

                            Toast.makeText(this,
                                    "Registered Successfully",
                                    Toast.LENGTH_SHORT).show();

                            // move immediately
                            Intent intent = new Intent(signup.this, get_start.class);



                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
        // Password toggle
        // Password toggle
        imageViewTogglePassword.setOnClickListener(v -> {

            if (isPasswordVisible) {

                editTextPassword.setTransformationMethod(
                        android.text.method.PasswordTransformationMethod.getInstance());

                imageViewTogglePassword.setImageResource(R.drawable.hide);

            } else {

                editTextPassword.setTransformationMethod(
                        android.text.method.HideReturnsTransformationMethod.getInstance());

                imageViewTogglePassword.setImageResource(R.drawable.unhide);
            }

            isPasswordVisible = !isPasswordVisible;

            // Cursor stays at end
            editTextPassword.setSelection(
                    editTextPassword.getText().length());
        });
    }
}
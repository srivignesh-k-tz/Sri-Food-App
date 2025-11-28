package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class third extends AppCompatActivity {

     EditText editTextPassword,edit_email;
     ImageView imageViewTogglePassword;
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(third.this, first.class);
                startActivity(intent);
            }
        });

        TextView signup1=findViewById(R.id.signup1);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(third.this, fourth.class);
                startActivity(intent);
            }
        });

        TextView forgotpassword=findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(third.this, sixth.class);
                startActivity(intent);
            }
        });

        edit_email = findViewById(R.id.edit_email);
        editTextPassword = findViewById(R.id.editTextPassword);


        TextView conti=findViewById(R.id.conti);
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textemail = edit_email.getText().toString();
                String textpass = editTextPassword.getText().toString();
                if (textemail.isEmpty() || textpass.isEmpty()) {
                    Toast.makeText(third.this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
                } else if (textpass.length() != 8) {
                    Toast.makeText(third.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(third.this, seven.class);
                    startActivity(intent);
                }
            }
        });

        imageViewTogglePassword = findViewById(R.id.imageViewTogglePassword);

        imageViewTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                // Hide Password
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imageViewTogglePassword.setImageResource(R.drawable.hide);
            } else {
                // Show Password
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imageViewTogglePassword.setImageResource(R.drawable.unhide);
            }
            isPasswordVisible = !isPasswordVisible;
            // Move the cursor to the end of the text
            editTextPassword.setSelection(editTextPassword.getText().length());
        });
    }
    }

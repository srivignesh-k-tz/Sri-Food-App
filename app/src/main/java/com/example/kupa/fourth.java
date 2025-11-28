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

public class fourth extends AppCompatActivity {
    private EditText editTextPassword,edit_name,edit_mail,edit_num;
    private ImageView imageViewTogglePassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);

        TextView signin3=findViewById(R.id.signin3);
        signin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(fourth.this, third.class);
                startActivity(intent);
            }
        });
        edit_mail = findViewById(R.id.edit_mail);
        editTextPassword = findViewById(R.id.editTextPassword);
        edit_num = findViewById(R.id.edit_num);
        edit_name = findViewById(R.id.edit_name);

        TextView register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textmail = edit_mail.getText().toString();
                String textpassword = editTextPassword.getText().toString();
                String textnum = edit_num.getText().toString();
                String textname = edit_name.getText().toString();

                if(textmail.isEmpty() || textpassword.isEmpty() || textnum.isEmpty() || textname.isEmpty()){
                    Toast.makeText(fourth.this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
                }
                else if (textnum.length() != 10) {
                    Toast.makeText(fourth.this, "Phone Number must be 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if (textpassword.length() != 8) {
                    Toast.makeText(fourth.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(fourth.this, seven.class);
                    startActivity(intent);
                }
            }
        });
        ImageView ba =findViewById(R.id.ba);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(fourth.this, second.class);
                startActivity(intent);
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
            editTextPassword.setSelection(editTextPassword.getText().length());
        });

    }
}
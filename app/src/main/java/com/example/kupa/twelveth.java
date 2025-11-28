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


public class twelveth extends AppCompatActivity {

    private EditText editTextPassword;
    private ImageView imageViewTogglePassword;
    private EditText confirm_password;
    private ImageView conpass_eye;
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_twelveth);

        ImageView backs=findViewById(R.id.backs);
        backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(twelveth.this, sixth.class);
                startActivity(intent);
            }
        });
        editTextPassword = findViewById(R.id.editTextPass);
        confirm_password = findViewById(R.id.confirm_password);

        TextView conti=findViewById(R.id.conti);
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textpass =editTextPassword.getText().toString();
                String textconpass=confirm_password.getText().toString();
                if (textpass.isEmpty() || textconpass.isEmpty()){
                    Toast.makeText(twelveth.this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
                }
                else if (textpass.length() !=8 || textconpass.length() !=8 ){
                    Toast.makeText(twelveth.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                } else if (!textpass.equals(textconpass)) {
                    Toast.makeText(twelveth.this, "New Password and Confirm Password are not Same", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(twelveth.this, third.class);
                    startActivity(intent);
                }
            }
        });
        imageViewTogglePassword = findViewById(R.id.imageViewTogglePass);

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
        confirm_password = findViewById(R.id.confirm_password);
        conpass_eye = findViewById(R.id.conpass_eye);

        conpass_eye.setOnClickListener(v -> {
            if (isPasswordVisible) {
                confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                conpass_eye.setImageResource(R.drawable.hide);
            } else {
                confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                conpass_eye.setImageResource(R.drawable.unhide);
            }
            isPasswordVisible = !isPasswordVisible;
            confirm_password.setSelection(confirm_password.getText().length());
        });

    }
}
package com.example.kupa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;

public class otp_email extends AppCompatActivity {

    PinView pinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_email);

        pinview = findViewById(R.id.pinview);

        TextView emailText = findViewById(R.id.emailText);

        String email = getIntent().getStringExtra("email");

        emailText.setText(email);

        TextView cont = findViewById(R.id.cont);

        cont.setOnClickListener(v -> {

            String enteredOtp =
                    pinview.getText().toString().trim();

            if (enteredOtp.length() != 4) {

                Toast.makeText(this,
                        "Enter valid OTP",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            SharedPreferences sp =
                    getSharedPreferences("OTP_PREF", MODE_PRIVATE);

            int savedOtp = sp.getInt("otp", 0);

            if (enteredOtp.equals(String.valueOf(savedOtp))) {

                Toast.makeText(this,
                        "OTP Verified",
                        Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(otp_email.this,
                                reset_password.class);

                intent.putExtra("email", email);

                startActivity(intent);

                finish();

            } else {

                Toast.makeText(this,
                        "Wrong OTP",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ImageView backemail = findViewById(R.id.backemail);

        backemail.setOnClickListener(v -> finish());
    }
}
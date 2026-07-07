package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.*;

import java.util.concurrent.TimeUnit;

public class otp_phone_number extends AppCompatActivity {

    PinView pinview;
    FirebaseAuth auth;
    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_phone_number);

        pinview = findViewById(R.id.pinview);
        auth = FirebaseAuth.getInstance();

        String phone = getIntent().getStringExtra("phone");

        TextView conti = findViewById(R.id.conti);
        ImageView backphone = findViewById(R.id.backphone);

        // SEND OTP AUTOMATICALLY
        sendOtp(phone);

        // VERIFY OTP
        conti.setOnClickListener(v -> {

            String code = pinview.getText().toString().trim();

            if (code.isEmpty() || code.length() != 4) {
                Toast.makeText(this, "Enter valid OTP", Toast.LENGTH_SHORT).show();
                return;
            }

            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(verificationId, code);

            auth.signInWithCredential(credential)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(otp_phone_number.this, reset_password.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(this,
                                    "Invalid OTP",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        backphone.setOnClickListener(v -> finish());
    }

    // 🔥 SEND REAL OTP
    private void sendOtp(String phone) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91" + phone) // change country if needed
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // Auto verification (sometimes happens)
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Toast.makeText(otp_phone_number.this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(String id,
                                                   PhoneAuthProvider.ForceResendingToken token) {

                                verificationId = id;

                                Toast.makeText(otp_phone_number.this,
                                        "OTP Sent",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
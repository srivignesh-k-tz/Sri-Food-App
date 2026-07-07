package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class forget_password extends AppCompatActivity {

    String generatedOtp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        LinearLayout email = findViewById(R.id.email);

        email.setOnClickListener(v -> {

            String userEmail = "test@gmail.com";

            generatedOtp = generateOtp();

            sendEmailOtp(userEmail, generatedOtp);
        });

        ImageView backk = findViewById(R.id.backk);

        backk.setOnClickListener(v -> {
            Intent intent = new Intent(forget_password.this, signing.class);
            startActivity(intent);
        });
    }

    // ================= OTP GENERATE =================

    private String generateOtp() {

        Random random = new Random();

        int otp = 1000 + random.nextInt(9000);

        return String.valueOf(otp);
    }

    // ================= SEND EMAIL =================

    private void sendEmailOtp(String receiverEmail, String otp) {

        new Thread(() -> {

            try {

                String senderEmail = "YOUR_GMAIL@gmail.com";
                String senderPassword = "YOUR_APP_PASSWORD";

                Properties properties = new Properties();

                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties,
                        new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {

                                return new PasswordAuthentication(
                                        senderEmail,
                                        senderPassword
                                );
                            }
                        });

                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(senderEmail));

                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(receiverEmail)
                );

                message.setSubject("OTP Verification");

                message.setText("Your OTP is: " + otp);

                // ✅ SEND EMAIL
                Transport.send(message);

                runOnUiThread(() -> {

                    Toast.makeText(
                            forget_password.this,
                            "OTP Sent Successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(
                            forget_password.this,
                            otp_email.class
                    );

                    intent.putExtra("email", receiverEmail);
                    intent.putExtra("otp", otp);

                    startActivity(intent);
                });

            } catch (Exception e) {

                e.printStackTrace();

                runOnUiThread(() -> Toast.makeText(
                        forget_password.this,
                        "Failed : " + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show());
            }

        }).start();
    }
}
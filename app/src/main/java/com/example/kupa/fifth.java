package com.example.kupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaos.view.PinView;

public class fifth extends AppCompatActivity {

    PinView pinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fifth);

        pinview=findViewById(R.id.pinview);

        TextView cont=findViewById(R.id.cont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textpin = pinview.getText().toString();

                if (textpin.length() !=4) {
                    Toast.makeText(fifth.this, "Pin must be 4 digits", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(fifth.this, twelveth.class);
                    startActivity(intent);
                }
            }
        });
        ImageView backemail=findViewById(R.id.backemail);
        backemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(fifth.this, sixth.class);
                startActivity(intent);
            }
        });
    }
}
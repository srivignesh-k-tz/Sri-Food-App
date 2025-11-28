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

public class eleventh extends AppCompatActivity {
    PinView pinview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eleventh);

        pinview=findViewById(R.id.pinview);

        ImageView backphone=findViewById(R.id.backphone);
        backphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(eleventh.this, sixth.class);
                    startActivity(intent);
            }
        });
        TextView conti=findViewById(R.id.conti);
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textpin = pinview.getText().toString();

                if (textpin.length() !=4) {
                    Toast.makeText(eleventh.this, "Pin must be 4 digits", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent=new Intent(eleventh.this, twelveth.class);
                    startActivity(intent);
                }

            }
        });
    }
}
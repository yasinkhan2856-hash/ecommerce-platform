package com.example.ykdev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourscreen);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, loginActivity.class);
            startActivity(intent);
        });
        TextView loginText = findViewById(R.id.login_text);
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, loginActivity.class);
            startActivity(intent);
        });
    }
}

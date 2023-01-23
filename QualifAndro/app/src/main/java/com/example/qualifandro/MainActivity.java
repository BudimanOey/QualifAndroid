package com.example.qualifandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signUpBtn;
    Button loginBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signUpBtn = findViewById(R.id.btnSignup);
        signUpBtn.setOnClickListener(e ->{
            Intent signUpIntent = new Intent(this, SignActivity.class);
            startActivity(signUpIntent);
        });

        loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(e -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
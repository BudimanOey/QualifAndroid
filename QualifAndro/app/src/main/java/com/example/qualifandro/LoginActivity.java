package com.example.qualifandro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qualifandro.Models.User;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        DBHelper db = new DBHelper(this);

        etEmail = findViewById(R.id.etEmailLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        login = findViewById(R.id.btnLogin);


        login.setOnClickListener(e ->{

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this, getString(R.string.must_fill_all_fields), Toast.LENGTH_SHORT).show();
            }else{
                boolean checkAcc = db.checkAccount(email, password);
                if(checkAcc == true){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    this.finish();
                }else{
                    Toast.makeText(this, getString(R.string.cred_not_found), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

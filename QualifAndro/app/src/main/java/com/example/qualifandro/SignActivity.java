package com.example.qualifandro;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SignActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etEmail;
    EditText etConfirmPass;
    EditText etPhone;
    Button btnSignUp;
    SmsManager smsManager;
    Integer sendSmsPermission;
    ArrayList<String> validationErrors = new ArrayList<>();
    DBHelper db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        btnSignUp = findViewById(R.id.btnSignupSignPage);
        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.etEmailSignup);
        etPassword = findViewById(R.id.etPasswordSignup);
        etConfirmPass = findViewById(R.id.etRePasswordSignup);
        etPhone = findViewById(R.id.etPhone);
        addValidationErrorMsg();
        db = new DBHelper(this);

        smsManager = SmsManager.getDefault();
        sendSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(sendSmsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, 1);
        }


        btnSignUp.setOnClickListener(e ->{
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPass = etConfirmPass.getText().toString();
            String phoneNum = etPhone.getText().toString();

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPass) || TextUtils.isEmpty(phoneNum)){
                Toast.makeText(SignActivity.this, validationErrors.get(0),Toast.LENGTH_SHORT).show();
            }else{
                if(password.equals(confirmPass)){
                    if(db.checkEmail(email) != false) {
                        Toast.makeText(SignActivity.this, validationErrors.get(1), Toast.LENGTH_SHORT).show();
                    }else if(db.checkPhone(phoneNum) != false){
                        Toast.makeText(SignActivity.this, validationErrors.get(3), Toast.LENGTH_SHORT).show();
                    }else {
                        db.insertUser(username, password, email, phoneNum);
                        Toast.makeText(SignActivity.this, getString(R.string.account_created_message), Toast.LENGTH_SHORT).show();
                        smsManager.sendTextMessage(phoneNum, null, getString(R.string.account_created_message), null, null);
                        Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(SignActivity.this, validationErrors.get(2), Toast.LENGTH_SHORT).show();
                }
                smsManager.sendTextMessage(phoneNum, null, "Account has been registered!", null, null);
            }
        });

    }

    private void addValidationErrorMsg(){
        validationErrors.add(getString(R.string.must_fill_all_fields));
        validationErrors.add(getString(R.string.email_used));
        validationErrors.add(getString(R.string.password_not_match));
        validationErrors.add(getString(R.string.phone_used));
    }

}

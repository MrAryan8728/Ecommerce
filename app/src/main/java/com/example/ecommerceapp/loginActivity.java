package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signin(View view) {
        startActivity(new Intent(loginActivity.this,MainActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(loginActivity.this,RegisterActivity.class));
    }
}
package com.example.ecommerceapp.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText email,password,name;
    private FirebaseAuth Auth;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Auth = FirebaseAuth.getInstance();
        if(Auth.getCurrentUser() != null)
        {
         startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",false);
        if(isFirstTime)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent i = new Intent(RegisterActivity.this, onboardingActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void signup(View view) {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userPassword = password.getText().toString();
        
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Enter Name !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(useremail))
        {
            Toast.makeText(this,"Enter Email !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty( userPassword))
        {
            Toast.makeText(this,"Enter Password !",Toast.LENGTH_SHORT).show();
            return;
        }
        if( userPassword.length() < 6)
        {
            Toast.makeText(this,"Password too short,Enter minimum 6 character !",Toast.LENGTH_SHORT).show();
        }
        Auth.createUserWithEmailAndPassword(useremail,userPassword).addOnCompleteListener(RegisterActivity.this, task -> {
          if(task.isSuccessful())
          {
              Toast.makeText(RegisterActivity.this,"Registration Successful:)",Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                      finish();
          }
          else
          {
              Toast.makeText(RegisterActivity.this,"Registration Failed :("+task.getException(),Toast.LENGTH_SHORT).show();
          }
        });
    }

    public void signin(View view) {
        startActivity(new Intent(RegisterActivity.this, loginActivity.class));
    }
}
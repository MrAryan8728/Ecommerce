package com.example.ecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText email,password;
    private FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Auth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
    }

    public void signin(View view) {
        String useremail = email.getText().toString();
        String userPassword = password.getText().toString();
        if(TextUtils.isEmpty(useremail))
        {
            Toast.makeText(this,"Enter Email Address !",Toast.LENGTH_SHORT).show();
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
        Auth.signInWithEmailAndPassword(useremail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(loginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(loginActivity.this,"Error : "+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void signup(View view) {
        startActivity(new Intent(loginActivity.this, RegisterActivity.class));
    }
}
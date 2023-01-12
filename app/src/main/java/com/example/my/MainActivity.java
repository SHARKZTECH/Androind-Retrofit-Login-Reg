package com.example.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText emailInp,passwordInp;
    Button loginBtn;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInp=findViewById(R.id.emailInp);
        passwordInp=findViewById(R.id.passInp);
        loginBtn=findViewById(R.id.loginBtn);
        btn=findViewById(R.id.btn);

         btn.setOnClickListener(view -> {
             Intent intent=new Intent(this,RegisterActivity.class);
             startActivity(intent);
         });

         loginBtn.setOnClickListener(view -> {
             String email=emailInp.getText().toString();
             String password=passwordInp.getText().toString();
             if(email.isEmpty() || password.isEmpty()){
                 Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show();
             }else{
                 //db code
                 Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(this,HomeActivity.class);
                 intent.putExtra("Email",email);
                 startActivity(intent);
             }
         });
    }
}
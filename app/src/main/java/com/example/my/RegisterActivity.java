package com.example.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText emailInp,passwordInp,pass2Inp,name;
    Button regBtn;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.nameInp);
        emailInp=findViewById(R.id.emailInp);
        passwordInp=findViewById(R.id.passInp);
        pass2Inp=findViewById(R.id.pass2Inp);
        regBtn=findViewById(R.id.registerBtn);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(view -> {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        regBtn.setOnClickListener(view -> {
            String userName=name.getText().toString();
            String email=emailInp.getText().toString();
            String pass=passwordInp.getText().toString();
            String pass2=pass2Inp.getText().toString();

            if(userName.isEmpty() || email.isEmpty() || pass.isEmpty() ||pass2.isEmpty()){
                Toast.makeText(this, "All field required!", Toast.LENGTH_SHORT).show();
            }else if(pass.equals(pass2)){
                //db code
                Toast.makeText(this, "A/c created!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "P don't match!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
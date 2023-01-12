package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String email=getIntent().getStringExtra("Email");
        TextView textView=findViewById(R.id.text);
        textView.setText("Welcome "+email+" !");
    }
}
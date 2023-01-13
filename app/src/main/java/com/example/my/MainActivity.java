package com.example.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText emailInp,passwordInp;
    Button loginBtn;
    AppCompatButton btn;
    RetrofitInterface retrofitInterface;
    private String BASE_URL="http://192.168.88.26:5000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInp=findViewById(R.id.emailInp);
        passwordInp=findViewById(R.id.passInp);
        loginBtn=findViewById(R.id.loginBtn);
        btn=findViewById(R.id.btn);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

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
                 HashMap<String,String> map=new HashMap<>();
                 map.put("email",email);
                 map.put("password",password);
                 Call<User> call=retrofitInterface.loginUser(map);
                 call.enqueue(new Callback<User>() {
                     @Override
                     public void onResponse(Call<User> call, Response<User> response) {
                         if (response.isSuccessful()) {
                             Toast.makeText(MainActivity.this, "Success Log", Toast.LENGTH_SHORT).show();
                             User user=response.body();
                             Log.d("Login", "onResponse: " + user);
                             Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                             intent.putExtra("Email",email);
                             startActivity(intent);
                         }else{
                             Toast.makeText(MainActivity.this,"Incorrect email/password", Toast.LENGTH_SHORT).show();
                             Log.d("Login", "onResponse: "+response.raw());
                         }
                     }
                     @Override
                     public void onFailure(Call<User> call, Throwable t) {
                         Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                         Log.d("Login", "onFailure: "+t);
                     }
                 });
             }
         });
    }
}
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

public class RegisterActivity extends AppCompatActivity {
    EditText emailInp,passwordInp,pass2Inp,name;
    Button regBtn;
    AppCompatButton btn;
    RetrofitInterface retrofitInterface;
    private String BASE_URL="http://192.168.88.26:5000/";


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
                Retrofit retrofit=new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                retrofitInterface=retrofit.create(RetrofitInterface.class);
                HashMap<String,String> map=new HashMap<>();
                map.put("name",userName);
                map.put("email",email);
                map.put("password",pass);
                Call<Void> call =retrofitInterface.registerUser(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "A/c created!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Log.d("Register",response.raw().toString());
                        }

                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Toast.makeText(this, "P don't match!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
package com.example.my;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

@POST("api/user/login")
    Call<User> loginUser(@Body HashMap<String,String> map);
@POST("api/user/register")
    Call<Void> registerUser(@Body HashMap<String,String> map);
}

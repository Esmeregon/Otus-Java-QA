package com.example.apihelpers;

import com.example.apihelpers.pojo.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("2")
    Call<User> getUser();

}

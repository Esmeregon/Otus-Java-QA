package com.example.apihelpers;

import com.example.apihelpers.pojo.createSingleUser.CreateUserRequest;
import com.example.apihelpers.pojo.createSingleUser.CreateUserResponse;
import com.example.apihelpers.pojo.singleResource.Resource;
import com.example.apihelpers.pojo.singleUser.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("users/2")
    Call<User> getUser();

    @GET("unknown/2")
    Call<Resource> getResource();

    @POST("users")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest body);

}

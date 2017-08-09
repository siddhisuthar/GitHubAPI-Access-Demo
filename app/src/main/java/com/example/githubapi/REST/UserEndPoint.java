package com.example.githubapi.REST;

import com.example.githubapi.Model.User;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by siddhi on 8/8/17.
 */

public interface UserEndPoint {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String user);

}


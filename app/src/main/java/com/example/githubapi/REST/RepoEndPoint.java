package com.example.githubapi.REST;

import com.example.githubapi.Model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by siddhi on 8/8/17.
 */

public interface RepoEndPoint {
    @GET("/users/{user}/repos")
    Call<List<Repo>> getRepo(@Path("user") String name);
}

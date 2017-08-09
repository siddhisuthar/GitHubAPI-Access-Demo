package com.example.githubapi.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.githubapi.Adapter.RepoAdapter;
import com.example.githubapi.Model.Repo;
import com.example.githubapi.R;
import com.example.githubapi.REST.APIClient;
import com.example.githubapi.REST.RepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by siddhi on 8/8/17.
 */

public class RepoActivity extends AppCompatActivity{

    String fetchedName;
    TextView nameTextView;
    RecyclerView mRecyclerView;
    List<Repo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        Bundle extras = getIntent().getExtras();
        //same login info from 1st screen
        fetchedName = extras.getString("username");

        nameTextView = (TextView) findViewById(R.id.usernameRepo);
        nameTextView.setText("User: " + fetchedName);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new RepoAdapter(myDataSource, R.layout.list_item_repo, getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();

    }

    public void loadRepositories(){

        RepoEndPoint repoService = APIClient.getClient().create(RepoEndPoint.class);
        Call<List<Repo>> repoCall = repoService.getRepo(fetchedName);
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

                Log.e("Repositories", t.toString());
            }
        });


    }
}

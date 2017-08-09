package com.example.githubapi.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapi.Model.User;
import com.example.githubapi.R;
import com.example.githubapi.REST.APIClient;
import com.example.githubapi.REST.UserEndPoint;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.githubapi.R.id.email;

public class UserActivity extends AppCompatActivity {

    private ImageView avatarImg;
    private TextView usernameTextView;
    private TextView followersTextView;
    private TextView followingTextView;
    private TextView loginNameTextView;
    private TextView emailTextView;
    private Button repositoriesBtn;

    Bundle extras;
    String uname;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.github_avatar);
        usernameTextView = (TextView) findViewById(R.id.username);
        followersTextView = (TextView) findViewById(R.id.followers);
        followingTextView = (TextView) findViewById(R.id.following);
        loginNameTextView = (TextView) findViewById(R.id.loginName);
        emailTextView = (TextView) findViewById(R.id.email);
        repositoriesBtn = (Button) findViewById(R.id.repositories);

        extras = getIntent().getExtras();
        uname = extras.getString("Username String");
        System.out.println(uname);
        loadData();

    }

    public void loadData(){

        UserEndPoint apiService = APIClient.getClient().create(UserEndPoint.class);
        Call<User> call = apiService.getUser(uname);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                ImageDownloader task = new ImageDownloader();
                try {

                    img = task.execute(response.body().getAvatar()).get();

                }
                catch (Exception e){
                    e.printStackTrace();

                }
                avatarImg.setImageBitmap(img);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    usernameTextView.setText("No name provided");
                }
                else {

                    usernameTextView.setText("Username: "+ response.body().getLogin());
                }


                loginNameTextView.setText("Name: "+ response.body().getName());
                followersTextView.setText("Followers: "+ response.body().getFollowers());
                followingTextView.setText("Following: "+ response.body().getFollowing());

                if(response.body().getEmail() == null){
                    emailTextView.setText("No email provided");
                } else{
                    emailTextView.setText("Email: " + response.body().getEmail());
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                System.out.println("Failed!" + t.toString());
            }
        });


    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void getRepositories(View view){

        Intent intent = new Intent(UserActivity.this, RepoActivity.class);
        intent.putExtra("username", uname);
        startActivity(intent);

    }
}

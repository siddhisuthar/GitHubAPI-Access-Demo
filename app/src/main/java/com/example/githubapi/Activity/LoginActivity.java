package com.example.githubapi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.githubapi.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button loginBtn;

    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameInput);
        loginBtn = (Button) findViewById(R.id.login);

    }

    public void getUserProfile(View view){
        i = new Intent(LoginActivity.this, UserActivity.class);
        i.putExtra("Username String", usernameEditText.getText().toString());

        startActivity(i);
    }
}

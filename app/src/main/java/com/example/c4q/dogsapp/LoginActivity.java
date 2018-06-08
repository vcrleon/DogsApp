package com.example.c4q.dogsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "loginCredentials";
    public static final String USERNAME_KEY = "username key";
    EditText usernameView;
    EditText passwordView;
    Button submit;
    SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();
        login = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        onSubmitClicked();

        //Check if a user is already logged in
        if(getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
                .getString(USERNAME_KEY, null) != null) {
            launchBreedsActivity();
            finish(); //we don't need this activity anymore
        }

//        String checkifUserNamePresent = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
//             .getString(USERNAME_KEY, null);
//
//        if(checkifUserNamePresent != null){
//            launchBreedsActivity();
//            finish();
//        }

    }

    public void setUpViews() {
        usernameView = findViewById(R.id.username_input);
        passwordView = findViewById(R.id.password_input);
        submit = findViewById(R.id.submit_button);
    }

    public void onSubmitClicked() {

        submit.setOnClickListener(new View.OnClickListener() { //an inner class
            @Override
            public void onClick(View v) {
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();

                if (username.isEmpty()) {
                    usernameView.setError("please enter a username");
                } else if (password.isEmpty()) {
                    passwordView.setError("please enter a password");
                } else if (password.toLowerCase().contains(username.toLowerCase())) {
                    passwordView.setError("password cannot contain username");
                } else {
                    SharedPreferences.Editor editor = login.edit();
                    editor.putString(USERNAME_KEY, username);
                    editor.apply();

//                    login.edit()  -------> method chaining
//                            .putString(USERNAME_KEY, username)
//                            .apply();

                    launchBreedsActivity();

                }
            }
        });

    }

    private void launchBreedsActivity(){
        Intent i = new Intent(LoginActivity.this, DogsActivity.class);
        startActivity(i);
    }

}

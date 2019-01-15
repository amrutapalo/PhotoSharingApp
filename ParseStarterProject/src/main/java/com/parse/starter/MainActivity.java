/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

  EditText usernameEditText;
  EditText passwordEditText;

  //boolean signUpModeActive=true;
  ////Button signUpButton=(Button) findViewById(R.id.signUpButton);
  //Button loginButton =(Button) findViewById(R.id.changeSignUpModeButton);


  /*public void changeSignUpModeClicked(View view) {


    if(signUpModeActive) {

      signUpModeActive=false;
      signUpButton.setText("Login");
      loginButton.setText("OR Sign up");

    }
    else

    {
      signUpModeActive=true;
      signUpButton.setText("Sign up");
      loginButton.setText("OR Login");

    }

  }*/

  //SHOW USER LIST ACTIVITY

public void showUserList(){
  Intent intent=new Intent(getApplicationContext(),UserListActivity.class);
  startActivity(intent);

}



  public void loginClicked(View view)
  {

    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
      Toast.makeText(this, "A username and a password are required.",Toast.LENGTH_SHORT).show();

    }


    ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {

        if(user!=null)
        {
          //Log.i("login","successful");
          //Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
          showUserList();


        }
        else {
          Toast.makeText(MainActivity.this, "Sorry!", Toast.LENGTH_SHORT).show();
        }

      }
    });



  }





  public void signUpClicked(View view) {


    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
      Toast.makeText(this, "A username and a password are required.",Toast.LENGTH_SHORT).show();

    }

     else {

      ParseUser user = new ParseUser();

      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("Signup", "Success");
            showUserList();
          } else {
            Toast.makeText(MainActivity.this, "Sorry!", Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
  }


      @Override
      protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ParseObject score = new ParseObject("Score");
        score.put("username","palo");
        score.put("score",86);
        score.saveEventually(new SaveCallback() {
          @Override
          public void done(ParseException e) {
            if(e== null)
            {
              Log.i("SaveInBackground","Successful");
            }
            else
            {
              Log.i("SaveInBackground","FAILED");


            }

          }
        });*/

        if(ParseUser.getCurrentUser()!=null)
        {
          showUserList();

        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
      }

    }
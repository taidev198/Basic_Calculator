package com.example.superme198.fundamental;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    /**Link:https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted*/

    EditText userName ;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.username_text);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(password.getText());
                new FetchURLAsyncTask(userName.getText().toString(), password.getText().toString(), getBaseContext()).execute();
            }
        });

    }

    public class FetchURLAsyncTask extends AsyncTask<Void, Void, Void> {
        public FetchURLAsyncTask(){}
        String username = " ";
        String password = " ";
        Context mContext;
        Handler handler = new Handler();
        Connection.Response loginForm;
        public FetchURLAsyncTask(String username, String password, Context context){
            this.username = username;
            this.password = password;
            mContext = context;
        }


        @Override
        protected Void doInBackground(Void... params) {

                   /* try {
                        login();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


        }



    }
}

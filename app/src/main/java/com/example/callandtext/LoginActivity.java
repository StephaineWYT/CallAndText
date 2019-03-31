package com.example.callandtext;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    //Define the components
    EditText et_username, et_password;
    Button btn_signUp, btn_logIn;
    TextView tv_str_welcome;

    //accepted data type
    String str_username, str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //initialize components
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_signUp = findViewById(R.id.btn_signUp);
        btn_logIn = findViewById(R.id.btn_logIn);
        tv_str_welcome = findViewById(R.id.tv_str_welcome);
        Log.i("tag", "initializing...");

        //when click sign up button
        btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //get values
                getValues();

                if (!checkValues()) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "incomplete information!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, WelcomeActivity.class);
                    intent.putExtra("tv_str_welcome", str_username + " , nice to meet you !");
                    startActivity(intent);
                }
            }
        });

        //when click log in button
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values
                getValues();

                if (!checkValues()) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "incomplete information!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, OperationActivity.class);
                    intent.putExtra("login_username", str_username + " , login successfully !");
                    startActivity(intent);
                }
            }
        });
    }

    public void getValues() {
        str_username = et_username.getText().toString().trim();
        Log.i("username", str_username);
        str_password = et_password.getText().toString().trim();
        Log.i("password", str_password);
    }

    public boolean checkValues() {
        //values are empty or phone number is illegal
        if (str_username.equals("") || str_password.equals(""))
            return false;
        return true;
    }

}

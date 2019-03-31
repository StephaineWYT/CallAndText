package com.example.callandtext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);

        //get the welcome string
        Intent get_intent = getIntent();
        String str_welcome = get_intent.getStringExtra("tv_str_welcome");

        //set the string
        TextView tv_str_welcome = findViewById(R.id.tv_str_welcome);
        tv_str_welcome.setText(str_welcome);
    }
}

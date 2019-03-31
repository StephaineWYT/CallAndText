package com.example.callandtext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TextActivity extends AppCompatActivity {
    //define components
    EditText et_text_content;
    Button btn_send;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textpage);

        init();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the value of number
                Intent get_intent = getIntent();
                String str_user_number = get_intent.getStringExtra("str_user_number");

                //get the content of message
                content = et_text_content.getText().toString();

                //check whether the content is empty
                if (TextUtils.isEmpty(content)) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "empty content !", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Log.i("text", content);
                }

                try {
                    //send message
                    /*SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(str_user_number, null, content, null, null);*/

                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + str_user_number));
                    intent.putExtra("sms_body", content);
                    startActivity(intent);

                    //notification
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "send successfully!", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {

                    //notification
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "failure, try again!", Toast.LENGTH_LONG).show();
                        }
                    });

                    e.printStackTrace();
                }
            }
        });
    }

    public void init() {
        et_text_content = findViewById(R.id.et_text_content);
        btn_send = findViewById(R.id.btn_send);
    }

}

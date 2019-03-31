package com.example.callandtext;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OperationActivity extends Activity {

    //define the components
    EditText et_phone;
    String str_user_number;
    Button btn_call, btn_text;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.operation);

        //get the welcome string
        getName();

        //refresh time
        getTime();

        //init the components
        init();

        //when click the call button
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_phone = et_phone.getText().toString().trim();
                if (checkNums(str_phone)) {
                    str_user_number = str_phone;
                    callNumber();
                } else {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "illegal phone number!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        //when click the call button
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_phone = et_phone.getText().toString().trim();
                if (checkNums(str_phone)) {
                    str_user_number = str_phone;
                    textNumber();
                } else {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "illegal phone number!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    public void getTime() {
        TextView tv_time = findViewById(R.id.tv_time);
        //refresh per second
        TimeThread timeThread = new TimeThread(tv_time);
        //start thread
        timeThread.start();
    }

    public void getName() {
        //get username
        Intent get_intent = getIntent();
        String str_username = get_intent.getStringExtra("login_username");

        //set the string
        TextView tv_username = findViewById(R.id.tv_username);
        tv_username.setText(str_username);
    }

    public static boolean checkNums(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return the detected string
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else
            return mobileNums.matches(telRegex);
    }

    public void init() {
        et_phone = findViewById(R.id.et_phone);
        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
    }

    public void callNumber() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + str_user_number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void textNumber() {
        Intent intent = new Intent(OperationActivity.this, TextActivity.class);
        intent.putExtra("str_user_number", str_user_number);
        startActivity(intent);
    }
}

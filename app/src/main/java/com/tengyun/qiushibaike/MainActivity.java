package com.tengyun.qiushibaike;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final int FINISH = 1;
    private ImageView iv_logo;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case FINISH:
                    iv_logo.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_logo = (ImageView) findViewById(R.id.iv_icon);

        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                handler.sendEmptyMessage(FINISH);
                SystemClock.sleep(500);
                startActivity(new Intent(MainActivity.this, HomeActivity.class));

            }
        }.start();
    }
}

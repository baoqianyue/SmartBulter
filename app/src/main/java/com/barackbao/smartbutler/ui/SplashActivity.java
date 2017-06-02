package com.barackbao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.barackbao.smartbutler.MainActivity;
import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.utils.ShareUtils;
import com.barackbao.smartbutler.utils.StaticClass;
import com.barackbao.smartbutler.utils.UtilTools;

/**
 * Created by BarackBao on 2017/6/1.
 */

public class SplashActivity extends AppCompatActivity {
    private TextView tv_splash;
    //延时handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断是否第一次进入应用
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.IS_First_USE, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, StaticClass.IS_First_USE, false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();

    }

    private void initViews() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);

        tv_splash = (TextView) findViewById(R.id.tv_splash);
        UtilTools.setFont(SplashActivity.this, tv_splash);
    }


    //在此页面禁止返回键

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}

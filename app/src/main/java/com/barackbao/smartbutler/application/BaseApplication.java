package com.barackbao.smartbutler.application;

import android.app.Application;

import com.barackbao.smartbutler.utils.StaticClass;

import cn.bmob.v3.Bmob;

/**
 * Created by BarackBao on 2017/5/30.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Bmob
        Bmob.initialize(getApplicationContext(), StaticClass.BMOB_APP_ID);
    }
}

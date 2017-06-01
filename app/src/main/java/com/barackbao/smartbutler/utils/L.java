package com.barackbao.smartbutler.utils;

import android.util.Log;

/**
 * Created by BarackBao on 2017/6/1.
 * 封装log
 */

public class L {

    //设置开关
    private final static boolean DEBUG = true;
    private final static String TAG = "Smartbutler";

    //四个等级 diwe
    //静态方法
    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }


}

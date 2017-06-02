package com.barackbao.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by BarackBao on 2017/5/30.
 * 全局工具类
 */

public class UtilTools {

    //为控件设置字体
    public static void setFont(Context mContext, TextView textView) {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Font.TTF");
        textView.setTypeface(typeface);
    }
}

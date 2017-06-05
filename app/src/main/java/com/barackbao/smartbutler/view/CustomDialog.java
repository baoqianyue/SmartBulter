package com.barackbao.smartbutler.view;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.barackbao.smartbutler.R;


/**
 * Created by BarackBao on 2017/6/4.
 */

public class CustomDialog extends Dialog {


    //定义模板
    public CustomDialog(Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                layout, style, Gravity.CENTER);
    }


    //设置属性

    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity, int anim) {
        super(context, style);

        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutparams = window.getAttributes();
        layoutparams.width = width;
        layoutparams.height = height;
        layoutparams.gravity = gravity;
        window.setAttributes(layoutparams);
        window.setWindowAnimations(anim);

    }

    //创建实例
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity){
        this(context,width,height,layout,style,gravity,R.style.pop_dialog_anim);
    }



}

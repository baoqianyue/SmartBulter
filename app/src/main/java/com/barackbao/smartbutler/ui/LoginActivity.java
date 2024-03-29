package com.barackbao.smartbutler.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.*;

import com.barackbao.smartbutler.MainActivity;
import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.utils.ShareUtils;
import com.barackbao.smartbutler.view.CustomDialog;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by BarackBao on 2017/6/3.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registered_btn;
    private EditText username_edt;
    private EditText password_edt;
    private Button login_in_btn;
    private CheckBox store_password_cb;
    private TextView login_forget_password_tv;
    private CustomDialog login_wait_cdi;
//    private Dialog login_wait_dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initViews();
    }

    private void initViews() {
        store_password_cb = (CheckBox) findViewById(R.id.store_password_cb);
        username_edt = (EditText) findViewById(R.id.login_username_edt);
        password_edt = (EditText) findViewById(R.id.login_password_edt);
        registered_btn = (Button) findViewById(R.id.login_registered_btn);
        login_in_btn = (Button) findViewById(R.id.login_in_btn);
        login_forget_password_tv = (TextView) findViewById(R.id.login_forget_password_tv);
        login_forget_password_tv.setOnClickListener(this);
        registered_btn.setOnClickListener(this);
        login_in_btn.setOnClickListener(this);
        login_wait_cdi = new CustomDialog(this, 50, 50, R.layout.dialog_loading, R.style.Theme_Dialog,
                Gravity.CENTER, R.style.pop_dialog_anim);
        //屏幕点击无效
        login_wait_cdi.setCancelable(false);

        //如果用户保存了密码，下次登录的时候就将保存的数据读取出来
        if (ShareUtils.getBoolean(this, "storepassword", true)) {
            username_edt.setText(ShareUtils.getString(this, "username", ""));
            password_edt.setText(ShareUtils.getString(this, "password", ""));
            store_password_cb.setChecked(ShareUtils.getBoolean(this, "storepassword", false));
        }
        //恢复上次checkBox的状态

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_registered_btn:
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                break;
            case R.id.login_in_btn:
                String username = username_edt.getText().toString().trim();
                String password = password_edt.getText().toString().trim();
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password)) {
                    login_wait_cdi.show();
                    final BmobUser user = new BmobUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.login(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            login_wait_cdi.dismiss();
                            if (e == null) {
                                if (user.getEmailVerified()) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请到邮箱验证账户", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败" + e.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_forget_password_tv:
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存是否保存的状态
        ShareUtils.putBoolean(this, "storepassword", store_password_cb.isChecked());

        if (store_password_cb.isChecked()) {
            ShareUtils.putString(this, "username", username_edt.getText().toString().trim());
            ShareUtils.putString(this, "password", password_edt.getText().toString().trim());
        } else {
            ShareUtils.deleShare(this, "username");
            ShareUtils.deleShare(this, "password");
        }
    }
}

package com.barackbao.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barackbao.smartbutler.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by BarackBao on 2017/6/3.
 */

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {

    private EditText username_edt;
    private EditText password_edt;
    private EditText password_again_edt;
    private EditText email_edt;
    private Button registered_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initViews();
    }

    private void initViews() {
        username_edt = (EditText) findViewById(R.id.username_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);
        password_again_edt = (EditText) findViewById(R.id.password_again_edt);
        email_edt = (EditText) findViewById(R.id.email_edt);
        registered_btn = (Button) findViewById(R.id.registered_btn);
        registered_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registered_btn:
                String username = username_edt.getText().toString().trim();
                String password = password_edt.getText().toString().trim();
                String password_again = password_again_edt.getText().toString().trim();
                String email = email_edt.getText().toString().trim();
                //判空
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password)
                        & !TextUtils.isEmpty(password_again) & !TextUtils.isEmpty(email)) {
                    //判断两次输入的密码是否一致
                    if (!password.equals(password_again)) {
                        Toast.makeText(this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        password_edt.setText("");
                        password_again_edt.setText("");
                    } else {
                        BmobUser user = new BmobUser();
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.signUp(new SaveListener<BmobUser>() {
                            @Override
                            public void done(BmobUser bmobUser, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisteredActivity.this, "注册失败" + e.toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
}

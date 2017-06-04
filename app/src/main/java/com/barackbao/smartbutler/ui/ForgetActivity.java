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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by BarackBao on 2017/6/3.
 */

public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    private Button forget_reset_password_btn;
    private Button forget_email_reset_btn;
    private EditText forget_old_edt;
    private EditText forget_new_edt;
    private EditText forget_new_again_edt;
    private EditText forget_email_edt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initViews();
    }

    private void initViews() {
        forget_old_edt = (EditText) findViewById(R.id.forget_old_edt);
        forget_new_edt = (EditText) findViewById(R.id.forget_new_edt);
        forget_new_again_edt = (EditText) findViewById(R.id.forget_new_again_edt);
        forget_email_edt = (EditText) findViewById(R.id.forget_email_edt);
        forget_email_reset_btn = (Button) findViewById(R.id.forget_email_reset_btn);
        forget_reset_password_btn = (Button) findViewById(R.id.forget_reset_btn);
        forget_reset_password_btn.setOnClickListener(this);
        forget_email_reset_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_email_reset_btn:
                //判空
                String email = forget_email_edt.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    //发送邮件
                    BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ForgetActivity.this, "邮件已发送，请注意查收",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgetActivity.this, "邮件发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_reset_btn:
                //判空
                String oldPassword = forget_old_edt.getText().toString().trim();
                String newPassword = forget_new_edt.getText().toString().trim();
                String newPasswordAgain = forget_new_again_edt.getText().toString().trim();
                if (!TextUtils.isEmpty(oldPassword) & !TextUtils.isEmpty(newPassword)
                        & !TextUtils.isEmpty(newPasswordAgain)) {
                    //判断两次输入的密码是否一致
                    if (newPassword.equals(newPasswordAgain)) {
                        //修改操作
                        BmobUser.updateCurrentUserPassword(oldPassword, newPassword,
                                new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(ForgetActivity.this, "密码修改成功",
                                                    Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(ForgetActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(this, "两次输入的新密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        forget_new_edt.setText("");
                        forget_new_again_edt.setText("");
                    }

                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

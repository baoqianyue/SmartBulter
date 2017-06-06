package com.barackbao.smartbutler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.ui.LoginActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by BarackBao on 2017/5/30.
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private ImageView user_icon_img;
    private TextView edit_user_tv;
    private EditText edit_username_edt;
    private Button edit_user_go_btn;
    private Button user_exit_login_btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        user_icon_img = (ImageView) view.findViewById(R.id.user_icon_img);
        edit_username_edt = (EditText) view.findViewById(R.id.edit_username_edt);
        edit_user_tv = (TextView) view.findViewById(R.id.edit_user_tv);
        edit_user_go_btn = (Button) view.findViewById(R.id.edit_user_go_btn);
        user_exit_login_btn = (Button) view.findViewById(R.id.user_exit_login_btn);
        user_exit_login_btn.setOnClickListener(this);
        edit_user_tv.setOnClickListener(this);
        edit_user_go_btn.setOnClickListener(this);


        //设置输入框不可编辑
        edit_username_edt.setEnabled(false);

        //设置内容
        BmobUser userInfo = BmobUser.getCurrentUser();
        edit_username_edt.setText(userInfo.getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_user_tv:
                edit_user_go_btn.setVisibility(View.VISIBLE);
                edit_username_edt.setEnabled(true);
                break;
            case R.id.user_exit_login_btn:
                BmobUser.logOut();
                BmobUser currentUser = BmobUser.getCurrentUser();//currentUser是null
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.edit_user_go_btn:
                String username = edit_username_edt.getText().toString();

                if (!TextUtils.isEmpty(username)) {
                    BmobUser newUser = new BmobUser();
                    newUser.setUsername(username);
                    BmobUser user = BmobUser.getCurrentUser();
                    newUser.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (null == e) {
                                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                edit_user_go_btn.setVisibility(View.GONE);
                                edit_username_edt.setEnabled(false);
                            } else {
                                Toast.makeText(getContext(), "修改失败" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "输入框不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

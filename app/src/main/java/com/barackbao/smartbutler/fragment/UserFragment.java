package com.barackbao.smartbutler.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.barackbao.smartbutler.view.CustomDialog;

import java.io.File;

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
    private CustomDialog photo_dialog;
    private Button dialog_photo_camera_btn;
    private Button dialog_photo_album_btn;
    private Button dialog_photo_cancel_btn;

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
        user_icon_img.setOnClickListener(this);
        user_exit_login_btn.setOnClickListener(this);
        edit_user_tv.setOnClickListener(this);
        edit_user_go_btn.setOnClickListener(this);

        //dialog
        photo_dialog = new CustomDialog(getContext(), 0, 0, R.layout.dialog_photo, R.style.pop_dialog_anim
                , Gravity.BOTTOM, 0);
        //提示框外无法点击
        photo_dialog.setCancelable(false);
        dialog_photo_camera_btn = (Button) photo_dialog.findViewById(R.id.dialog_photo_camera);
        dialog_photo_album_btn = (Button) photo_dialog.findViewById(R.id.dialog_photo_album);
        dialog_photo_cancel_btn = (Button) photo_dialog.findViewById(R.id.dialog_photo_cancel);
        dialog_photo_album_btn.setOnClickListener(this);
        dialog_photo_cancel_btn.setOnClickListener(this);
        dialog_photo_camera_btn.setOnClickListener(this);
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
            case R.id.user_icon_img:
                photo_dialog.show();
                break;
            case R.id.dialog_photo_camera:
                toCamera();
                break;
            case R.id.dialog_photo_album:
                toAlbum();
                break;
            case R.id.dialog_photo_cancel:
                photo_dialog.dismiss();
                break;
        }
    }

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int ALBUM_REQUEST_CODE = 101;


    private void toAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
        photo_dialog.dismiss();
    }

    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        photo_dialog.dismiss();
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        //判断内存卡是否有空间
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
//                PHOTO_IMAGE_FILE_NAME)));
//        startActivityForResult(intent, CAMERA_REQUEST_CODE);
//        photo_dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                //进入相册
                case ALBUM_REQUEST_CODE:
                    cutImage(data.getData());
                    break;
                //进入相机
                case CAMERA_REQUEST_CODE:
                    File tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    cutImage(Uri.fromFile(tempFile));
                    break;
            }
        }
    }

    private void cutImage(Uri uri) {
    }
}

package com.barackbao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.barackbao.smartbutler.R;

/**
 * Created by BarackBao on 2017/6/3.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registered_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initViews();
    }

    private void initViews() {
        registered_btn = (Button) findViewById(R.id.registered_btn);
        registered_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
    }
}

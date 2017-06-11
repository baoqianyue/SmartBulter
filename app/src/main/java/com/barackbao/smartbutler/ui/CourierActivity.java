package com.barackbao.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.barackbao.smartbutler.adapter.CourierAdapter;
import com.barackbao.smartbutler.entity.CourierBean;
import com.barackbao.smartbutler.utils.StaticClass;

import com.barackbao.smartbutler.R;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BarackBao on 2017/6/10.
 */

public class CourierActivity extends BaseActivity implements View.OnClickListener {
    private EditText courier_name_edt;
    private EditText courier_number_edt;
    private Button courier_go_btn;
    private ListView courier_content_lv;
    private CourierBean bean;
    private List<CourierBean> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initViews();
    }

    private void initViews() {
        courier_name_edt = (EditText) findViewById(R.id.courier_name_edt);
        courier_number_edt = (EditText) findViewById(R.id.courier_number_edt);
        courier_go_btn = (Button) findViewById(R.id.courier_go_btn);
        courier_content_lv = (ListView) findViewById(R.id.courier_content_lv);
        courier_go_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.courier_go_btn:
                String courierCompany = courier_name_edt.getText().toString();
                String courierNumber = courier_number_edt.getText().toString();
                if (!TextUtils.isEmpty(courierCompany) && !TextUtils.isEmpty(courierNumber)) {
                    //拼接url
                    String url = "http://v.juhe.cn/exp/index?key="
                            + StaticClass.COURIER_KEY
                            + "&com="
                            + courierCompany
                            + "&no="
                            + courierNumber;
                    //使用RxVolley请求数据
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            Toast.makeText(CourierActivity.this, t, Toast.LENGTH_SHORT).show();
                            //解析json数据
                            getJsonData(t);

                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void getJsonData(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject object = (JSONObject) list.get(i);
                bean.setRemark(object.getString("remark"));
                bean.setCity(object.getString("zone"));
                bean.setDate(object.getString("datetime"));
                mList.add(bean);
            }
            //倒序
            Collections.reverse(mList);
            courier_content_lv.setAdapter(new CourierAdapter(CourierActivity.this, mList));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

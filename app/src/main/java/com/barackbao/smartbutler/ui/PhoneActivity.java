package com.barackbao.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BarackBao on 2017/6/14.
 */

public class PhoneActivity extends BaseActivity implements View.OnClickListener {
    private EditText phone_number_edt;
    private ImageView phone_company_imv;
    private TextView phone_result_tv;
    private Button phone_search_btn;
    private String phoneNumber;
    private static final String TAG = "PhoneActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        initViews();
    }

    private void initViews() {
        phone_number_edt = (EditText) findViewById(R.id.phone_number_edt);
        phone_company_imv = (ImageView) findViewById(R.id.phone_company_imv);
        phone_result_tv = (TextView) findViewById(R.id.phone_result_tv);
        phone_search_btn = (Button) findViewById(R.id.phone_search_btn);
        phone_search_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_search_btn:
                phoneNumber = phone_number_edt.getText().toString();
                if (!TextUtils.isEmpty(phoneNumber)) {
                    searchNumber();
                }
        }
    }

    private void searchNumber() {
        String url = "http://apis.juhe.cn/mobile/get?phone="
                + phoneNumber
                + "&key="
                + StaticClass.PHONE_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                parseJson(t);
            }
        });
    }

    private void parseJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject result = jsonObject.getJSONObject("result");
            String province = result.getString("province");
            String city = result.getString("city");
            String company = result.getString("company");
            String card = result.getString("card");

            //设置数据
            phone_result_tv.setText("省：" + province + "\n"
                    + "城市：" + city + "\n"
                    + "运营商：" + company + "\n"
                    + "卡片类型：" + card + "\n");
            //设置图片
            switch (company) {
                case "电信":
                    phone_company_imv.setImageResource(R.drawable.chinatelecom);
                    break;
                case "联通":
                    phone_company_imv.setImageResource(R.drawable.chinaunion);
                    break;
                case "移动":
                    phone_company_imv.setImageResource(R.drawable.chinamobile);
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

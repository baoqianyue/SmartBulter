package com.barackbao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.barackbao.smartbutler.R;

/**
 * Created by BarackBao on 2017/7/3.
 */

public class WechatContentActivity extends BaseActivity {
    private ProgressBar wechatcontent_pb;
    private WebView wechatcontent_wv;
    public static final String TAG = "WechatContentActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechatcontent);


        initViews();
    }

    private void initViews() {
        wechatcontent_pb = (ProgressBar) findViewById(R.id.wechatcontent_pb);
        wechatcontent_wv = (WebView) findViewById(R.id.wechatcontent_wv);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        Log.i(TAG, url);

        getSupportActionBar().setTitle(title);

        wechatcontent_wv.getSettings().setJavaScriptEnabled(true);
        wechatcontent_wv.getSettings().setSupportZoom(true);
        wechatcontent_wv.getSettings().setBuiltInZoomControls(true);



        //设置进度回调
        wechatcontent_wv.setWebChromeClient(new WebClient());
        wechatcontent_wv.loadUrl(url);
        //设置在本应用中显示网页
        wechatcontent_wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    public class WebClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                wechatcontent_pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}

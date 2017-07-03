package com.barackbao.smartbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.adapter.WechatAdapter;
import com.barackbao.smartbutler.entity.WechatData;
import com.barackbao.smartbutler.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BarackBao on 2017/5/30.
 */

public class WechatFragment extends Fragment {
    private RecyclerView wechatContent_rv;
    private List<WechatData> mList = new ArrayList<>();
    private WechatData mData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        wechatContent_rv = (RecyclerView) view.findViewById(R.id.wechat_rlv);

        String url = StaticClass.WECHAT_URL + StaticClass.WECHAT_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Toast.makeText(getContext(), t, Toast.LENGTH_SHORT).show();
                //解析数据
                parseJson(t);
            }
        });


    }

        private void parseJson(String t) {
            try {
                JSONObject jsonObject = new JSONObject(t);
                JSONObject object = jsonObject.getJSONObject("result");
                JSONArray list = object.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject data = (JSONObject) list.get(i);
                    mData = new WechatData();
                    mData.setImgUrl(data.getString("firstImg"));
                    mData.setTitle(data.getString("title"));
                    mData.setSource(data.getString("source"));
                    mList.add(mData);
                }
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                wechatContent_rv.setLayoutManager(manager);
                WechatAdapter adapter = new WechatAdapter(getContext(),mList);
                wechatContent_rv.setAdapter(adapter);
                adapter.setOnItemClickListener(new WechatAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(), mList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}

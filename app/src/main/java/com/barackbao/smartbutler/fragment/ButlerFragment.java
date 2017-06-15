package com.barackbao.smartbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.adapter.ButlerChatAdapter;
import com.barackbao.smartbutler.entity.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BarackBao on 2017/5/30.
 */

public class ButlerFragment extends Fragment implements View.OnClickListener {

    private ListView butler_chat_lv;
    private Button butler_left_btn;
    private Button butler_right_btn;
    private List<ChatData> mChatDatas = new ArrayList<>();
    private ButlerChatAdapter mChatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bulter, null);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        butler_chat_lv = (ListView) view.findViewById(R.id.butler_chat_lv);
        butler_left_btn = (Button) view.findViewById(R.id.butler_left_btn);
        butler_right_btn = (Button) view.findViewById(R.id.butler_right_btn);
        butler_left_btn.setOnClickListener(this);
        butler_right_btn.setOnClickListener(this);

        mChatAdapter = new ButlerChatAdapter(getContext(), mChatDatas);
        butler_chat_lv.setAdapter(mChatAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butler_left_btn:

                break;
            case R.id.butler_right_btn:

                break;
        }
    }
}

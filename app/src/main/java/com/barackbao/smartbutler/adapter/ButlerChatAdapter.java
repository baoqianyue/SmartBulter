package com.barackbao.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.entity.ChatData;

import java.util.List;

/**
 * Created by BarackBao on 2017/6/15.
 */

public class ButlerChatAdapter extends BaseAdapter {

    private static final int CHAT_LEFT_VALUE = 1;
    private static final int CHAT_RIGHT_VALUE = 2;


    private Context mContext;
    private List<ChatData> mList;
    private ChatData mData;
    private LayoutInflater mInflater;

    public ButlerChatAdapter(Context context, List<ChatData> list) {
        this.mContext = context;
        this.mList = list;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLeft viewHolderLeft = null;
        ViewHolderRight viewHolderRight = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case CHAT_LEFT_VALUE:
                    viewHolderLeft = new ViewHolderLeft();
                    convertView = mInflater.inflate(R.layout.butler_left_item, parent, false);

                    convertView.setTag(viewHolderLeft);
                    break;
                case CHAT_RIGHT_VALUE:
                    viewHolderRight = new ViewHolderRight();
                    convertView = mInflater.inflate(R.layout.butler_right_item, parent, false);

                    convertView.setTag(viewHolderRight);
                    break;
            }
        } else {
            switch (type) {
                case CHAT_LEFT_VALUE:
                    viewHolderLeft = (ViewHolderLeft) convertView.getTag();
                    break;
                case CHAT_RIGHT_VALUE:
                    viewHolderRight = (ViewHolderRight) convertView.getTag();
                    break;
            }

        }

        /**
         * 设置数据
         */
        mData = mList.get(position);
        switch (type) {
            case CHAT_LEFT_VALUE:

                break;
            case CHAT_RIGHT_VALUE:

                break;
        }


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        ChatData chatData = mList.get(position);
        int type = chatData.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class ViewHolderLeft {
        private TextView text_item_left;
    }

    class ViewHolderRight {
        private TextView text_item_right;
    }


}

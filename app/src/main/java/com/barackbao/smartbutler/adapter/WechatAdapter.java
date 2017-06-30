package com.barackbao.smartbutler.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.entity.WechatData;

import java.util.List;

/**
 * Created by BarackBao on 2017/6/30.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.WechatListViewHolder> {
    private Context mContext;
    private List<WechatData> mDataList;
    private WechatData mData;


    class WechatListViewHolder extends RecyclerView.ViewHolder{
        private ImageView wechat_content_img;
        private TextView wechat_title_tv;
        private TextView wechat_source_tv;

        public WechatListViewHolder(View itemView) {
            super(itemView);
            wechat_content_img = (ImageView) itemView.findViewById(R.id.wechat_content_img);
            wechat_title_tv = (TextView) itemView.findViewById(R.id.wechat_content_title_tv);
            wechat_source_tv = (TextView) itemView.findViewById(R.id.wechat_content_source_tv);
        }
        private void bindView(WechatData data){
            mData = data;
            wechat_content_img.setImageURI(Uri.parse(data.getImgUrl()));
            wechat_title_tv.setText(data.getTitle());
            wechat_source_tv.setText(data.getSource());

        }
    }

    public WechatAdapter(Context context,List<WechatData> mlist){
        this.mContext =  context;
        this.mDataList = mlist;
    }

    @Override
    public WechatListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.wechat_item,parent,false);
        return new WechatListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WechatListViewHolder holder, int position) {
        mData = mDataList.get(position);
        holder.bindView(mData);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}

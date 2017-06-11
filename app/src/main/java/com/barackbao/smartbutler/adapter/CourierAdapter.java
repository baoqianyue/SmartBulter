package com.barackbao.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.barackbao.smartbutler.R;
import com.barackbao.smartbutler.entity.CourierBean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by BarackBao on 2017/6/11.
 */

public class CourierAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourierBean> mList;
    //布局管理器
    private LayoutInflater mInflater;
    private CourierBean Bean;

    public CourierAdapter(Context context, List<CourierBean> list) {
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
        ViewHolder holder = null;
        //判断是否第一次加载
        if (convertView == null) {
            holder = new ViewHolder();
            mInflater.inflate(R.layout.courier_content_item, parent, false);
            holder.courier_remark_tv = (TextView) convertView.findViewById(R.id.courier_remark_tv);
            holder.courier_city_tv = (TextView) convertView.findViewById(R.id.courier_city_tv);
            holder.courier_date_tv = (TextView) convertView.findViewById(R.id.courier_date_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        Bean = mList.get(position);
        holder.courier_remark_tv.setText(Bean.getRemark().toString());
        holder.courier_city_tv.setText(Bean.getCity().toString());
        holder.courier_date_tv.setText(Bean.getDate().toString());


        return convertView;
    }


    class ViewHolder {

        private TextView courier_remark_tv;
        private TextView courier_city_tv;
        private TextView courier_date_tv;

    }
}

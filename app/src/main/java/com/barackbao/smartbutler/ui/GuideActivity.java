package com.barackbao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.barackbao.smartbutler.MainActivity;

import com.barackbao.smartbutler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BarackBao on 2017/6/1.
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<View> mViews = new ArrayList<>();
    private View view1, view2, view3;
    //三个小圆点
    private ImageView item1, item2, item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        view1 = View.inflate(this, R.layout.guide_item_one, null);
        view2 = View.inflate(this, R.layout.guide_item_two, null);
        view3 = View.inflate(this, R.layout.guide_item_three, null);
        view3.findViewById(R.id.goto_app_btn).setOnClickListener(this);

        item1 = (ImageView) findViewById(R.id.item1);
        item2 = (ImageView) findViewById(R.id.item2);
        item3 = (ImageView) findViewById(R.id.item3);
        //设置默认图
        setItemIcon(true, false, false);

        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);


        mViewPager.setAdapter(new pagerAdapter());

        //监听viewpager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //根据当前位置设置小圆点
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setItemIcon(true, false, false);
                        break;
                    case 1:
                        setItemIcon(false, true, false);
                        break;
                    case 2:
                        setItemIcon(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setItemIcon(boolean checkItem1, boolean checkItem2, boolean checkItem3) {
        if (checkItem1) {
            item1.setBackgroundResource(R.drawable.item_on);
        } else {
            item1.setBackgroundResource(R.drawable.item_off);
        }
        if (checkItem2) {
            item2.setBackgroundResource(R.drawable.item_on);
        } else {
            item2.setBackgroundResource(R.drawable.item_off);
        }
        if (checkItem3) {
            item3.setBackgroundResource(R.drawable.item_on);
        } else {
            item3.setBackgroundResource(R.drawable.item_off);
        }

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }

    private class pagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 这个函数只要能将视图和位置对应起来就ok，返回一个能确定位置的key值就行
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
//            super.destroyItem(container, position, object);
        }
    }
}

package com.barackbao.smartbutler.entity;

/**
 * Created by BarackBao on 2017/6/11.
 */

public class CourierBean {

    private String date;
    //状态
    private String remark;
    private String City;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "CourierBean{" +
                "date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}

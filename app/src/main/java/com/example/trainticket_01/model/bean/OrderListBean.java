package com.example.trainticket_01.model.bean;

import android.os.Parcel;

import java.util.List;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 订单列表实体类
 */

public class OrderListBean extends ResultBean
{
    private List<OrderBean> orders;

    public List<OrderBean> getOrders()
    {
        return orders;
    }

    public void setOrders(List<OrderBean> orders)
    {
        this.orders = orders;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.orders);
    }

    public OrderListBean()
    {
    }

    protected OrderListBean(Parcel source)
    {
        super(source);
        this.orders = source.createTypedArrayList(OrderBean.CREATOR);
    }

    public static final Creator<OrderListBean> CREATOR = new Creator<OrderListBean>()
    {
        @Override
        public OrderListBean createFromParcel(Parcel source)
        {
            return new OrderListBean(source);
        }

        @Override
        public OrderListBean[] newArray(int size)
        {
            return new OrderListBean[size];
        }
    };
}

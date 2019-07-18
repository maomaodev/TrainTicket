package com.example.trainticket_01.model.bean;

import android.os.Parcel;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 订单实体类
 */

public class OrderBean extends ResultBean
{
    private String ticketId;    // 车票ID
    private String orderId; // 订单ID
    private String userId;  // 用户ID
    private String account; // 账户
    private String realName;    // 真实姓名
    private String shift;   // 车次
    private String fromStation; // 出发地
    private String startTime;   // 出发时间
    private String toStation;   // 目的地
    private String endTime; // 到站时间
    private String date;    // 日期
    private String ticketType;  // 席别
    private String carriage;    // 车厢
    private String seat;    // 座位号
    private String price;   // 票价
    private String userType;    // 用户类型
    private String state;   // 订单状态

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getShift()
    {
        return shift;
    }

    public void setShift(String shift)
    {
        this.shift = shift;
    }

    public String getFromStation()
    {
        return fromStation;
    }

    public void setFromStation(String fromStation)
    {
        this.fromStation = fromStation;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getToStation()
    {
        return toStation;
    }

    public void setToStation(String toStation)
    {
        this.toStation = toStation;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTicketType()
    {
        return ticketType;
    }

    public void setTicketType(String ticketType)
    {
        this.ticketType = ticketType;
    }

    public String getCarriage()
    {
        return carriage;
    }

    public void setCarriage(String carriage)
    {
        this.carriage = carriage;
    }

    public String getSeat()
    {
        return seat;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public OrderBean()
    {
    }

    protected OrderBean(Parcel source)
    {
        super(source);
        this.ticketId = source.readString();
        this.orderId = source.readString();
        this.userId = source.readString();
        this.account = source.readString();
        this.realName = source.readString();
        this.shift = source.readString();
        this.fromStation = source.readString();
        this.startTime = source.readString();
        this.toStation = source.readString();
        this.endTime = source.readString();
        this.date = source.readString();
        this.ticketType = source.readString();
        this.carriage = source.readString();
        this.seat = source.readString();
        this.price = source.readString();
        this.userType = source.readString();
        this.state = source.readString();
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
        dest.writeString(this.ticketId);
        dest.writeString(this.orderId);
        dest.writeString(this.userId);
        dest.writeString(this.account);
        dest.writeString(this.realName);
        dest.writeString(this.shift);
        dest.writeString(this.fromStation);
        dest.writeString(this.startTime);
        dest.writeString(this.toStation);
        dest.writeString(this.endTime);
        dest.writeString(this.date);
        dest.writeString(this.ticketType);
        dest.writeString(this.carriage);
        dest.writeString(this.seat);
        dest.writeString(this.price);
        dest.writeString(this.userType);
        dest.writeString(this.state);

    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>()
    {
        @Override
        public OrderBean createFromParcel(Parcel source)
        {
            return new OrderBean(source);
        }

        @Override
        public OrderBean[] newArray(int size)
        {
            return new OrderBean[size];
        }
    };
}

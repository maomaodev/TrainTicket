package com.example.trainticket_01.model.bean;

import android.os.Parcel;

/**
 * Created by maomao on 2019/4/7.
 * <p>
 * 车票信息实体类
 */

public class TicketBean extends ResultBean
{
    private String shift;   // 车次
    private String startStationName;    // 出发站
    private String toStationName;   // 目的地
    private String startDate;   // 出发日期
    private String startTime;   // 出发时间
    private String time;    // 历时
    private String arriveTime;  // 到达时间

    private String wzTicketId;  // 无座车票ID
    private String yzTicketId;  // 硬座车票ID
    private String ywTicketId;  // 硬卧车票ID
    private String rwTicketId;  // 软卧车票ID
    private String zyTicketId;  // 一等座票ID
    private String zeTicketId;  // 商务座票ID

    private String wzNum;   // 无座数量
    private String yzNum;   // 硬座数量
    private String ywNum;   // 硬卧数量
    private String rwNum;   // 软卧数量
    private String zyNum;   // 一等座数量
    private String zeNum;   // 商务座数量

    private String wzPrice; // 无座价格
    private String yzPrice; // 硬座价格
    private String ywPrice; // 硬卧价格
    private String rwPrice; // 软卧价格
    private String zyPrice; // 一等座价格
    private String zePrice; // 商务座价格

    protected TicketBean(Parcel source)
    {
        super(source);
        this.shift = source.readString();
        this.startStationName = source.readString();
        this.toStationName = source.readString();
        this.startDate = source.readString();
        this.startTime = source.readString();
        this.time = source.readString();
        this.arriveTime = source.readString();

        this.wzTicketId = source.readString();
        this.yzTicketId = source.readString();
        this.ywTicketId = source.readString();
        this.rwTicketId = source.readString();
        this.zyTicketId = source.readString();
        this.zeTicketId = source.readString();

        this.wzNum = source.readString();
        this.yzNum = source.readString();
        this.ywNum = source.readString();
        this.rwNum = source.readString();
        this.zyNum = source.readString();
        this.zeNum = source.readString();

        this.wzPrice = source.readString();
        this.yzPrice = source.readString();
        this.ywPrice = source.readString();
        this.rwPrice = source.readString();
        this.zyPrice = source.readString();
        this.zePrice = source.readString();
    }

    public String getShift()
    {
        return shift;
    }

    public String getStartStationName()
    {
        return startStationName;
    }

    public String getToStationName()
    {
        return toStationName;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getTime()
    {
        return time;
    }

    public String getArriveTime()
    {
        return arriveTime;
    }

    public String getWzTicketId()
    {
        return wzTicketId;
    }

    public String getYzTicketId()
    {
        return yzTicketId;
    }

    public String getYwTicketId()
    {
        return ywTicketId;
    }

    public String getRwTicketId()
    {
        return rwTicketId;
    }

    public String getZyTicketId()
    {
        return zyTicketId;
    }

    public String getZeTicketId()
    {
        return zeTicketId;
    }

    public String getWzNum()
    {
        return wzNum;
    }

    public String getYzNum()
    {
        return yzNum;
    }

    public String getYwNum()
    {
        return ywNum;
    }

    public String getRwNum()
    {
        return rwNum;
    }

    public String getZyNum()
    {
        return zyNum;
    }

    public String getZeNum()
    {
        return zeNum;
    }

    public String getWzPrice()
    {
        return wzPrice;
    }

    public String getYzPrice()
    {
        return yzPrice;
    }

    public String getYwPrice()
    {
        return ywPrice;
    }

    public String getRwPrice()
    {
        return rwPrice;
    }

    public String getZyPrice()
    {
        return zyPrice;
    }

    public String getZePrice()
    {
        return zePrice;
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
        dest.writeString(this.shift);
        dest.writeString(this.startStationName);
        dest.writeString(this.toStationName);
        dest.writeString(this.startDate);
        dest.writeString(this.startTime);
        dest.writeString(this.time);
        dest.writeString(this.arriveTime);

        dest.writeString(this.wzTicketId);
        dest.writeString(this.yzTicketId);
        dest.writeString(this.ywTicketId);
        dest.writeString(this.rwTicketId);
        dest.writeString(this.zyTicketId);
        dest.writeString(this.zeTicketId);

        dest.writeString(this.wzNum);
        dest.writeString(this.yzNum);
        dest.writeString(this.ywNum);
        dest.writeString(this.rwNum);
        dest.writeString(this.zyNum);
        dest.writeString(this.zeNum);

        dest.writeString(this.wzPrice);
        dest.writeString(this.yzPrice);
        dest.writeString(this.ywPrice);
        dest.writeString(this.rwPrice);
        dest.writeString(this.zyPrice);
        dest.writeString(this.zePrice);
    }

    public static final Creator<TicketBean> CREATOR = new Creator<TicketBean>()
    {
        @Override
        public TicketBean createFromParcel(Parcel source)
        {
            return new TicketBean(source);
        }

        @Override
        public TicketBean[] newArray(int size)
        {
            return new TicketBean[size];
        }
    };
}
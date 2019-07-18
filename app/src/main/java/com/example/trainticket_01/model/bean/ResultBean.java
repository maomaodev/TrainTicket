package com.example.trainticket_01.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 一般结果实体类
 */

public class ResultBean implements Parcelable
{
    private String resStatus;   // 登录状态：failed/success
    private String resMsg;  // 登录信息：账号或密码错误/登录成功

    public ResultBean()
    {
    }

    protected ResultBean(Parcel source)
    {
        this.resStatus = source.readString();
        this.resMsg = source.readString();
    }

    public String getResStatus()
    {
        return resStatus;
    }

    public void setResStatus(String resStatus)
    {
        this.resStatus = resStatus;
    }

    public String getResMsg()
    {
        return resMsg;
    }

    public void setResMsg(String resMsg)
    {
        this.resMsg = resMsg;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    // 将数据写入Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(resStatus);
        dest.writeString(resMsg);
    }

    public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>()
    {
        // 从Parcel中读取数据
        @Override
        public ResultBean createFromParcel(Parcel source)
        {
            return new ResultBean(source);
        }

        // 供外部类反序列化本类数组使用
        @Override
        public ResultBean[] newArray(int size)
        {
            return new ResultBean[size];
        }
    };
}

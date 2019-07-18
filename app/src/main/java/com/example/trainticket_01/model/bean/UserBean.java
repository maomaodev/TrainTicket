package com.example.trainticket_01.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 用户实体类
 */

public class UserBean extends ResultBean
{
    private String userId;  // 用户ID
    private String account; // 账号
    private String password;    // 密码
    private String realName;    // 真实姓名
    private String idNumber;    // 身份证号
    private String userType;    // 用户类型

    public UserBean()
    {
    }

    protected UserBean(Parcel source)
    {
        this.userId = source.readString();
        this.account = source.readString();
        this.password = source.readString();
        this.realName = source.readString();
        this.idNumber = source.readString();
        this.userType = source.readString();
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getIdNumber()
    {
        return idNumber;
    }

    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.userId);
        dest.writeString(this.account);
        dest.writeString(this.password);
        dest.writeString(this.realName);
        dest.writeString(this.idNumber);
        dest.writeString(this.userType);
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>()
    {
        @Override
        public UserBean createFromParcel(Parcel source)
        {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size)
        {
            return new UserBean[size];
        }
    };
}

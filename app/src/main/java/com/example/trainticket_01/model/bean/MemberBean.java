package com.example.trainticket_01.model.bean;

import android.os.Parcel;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 联系人实体类
 */

public class MemberBean extends ResultBean
{
    private int id; // ID
    private String userId;  // 用户ID
    private String memberRealName;  // 联系人真实姓名
    private String memberIdNumber;  // 联系人身份证号
    private boolean isChecked = false;  // 是否选中

    public MemberBean()
    {
    }

    protected MemberBean(Parcel source)
    {
        super(source);
        this.id = source.readInt();
        this.userId = source.readString();
        this.memberRealName = source.readString();
        this.memberIdNumber = source.readString();
        this.isChecked = source.readByte() != 0;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getMemberRealName()
    {
        return memberRealName;
    }

    public void setMemberRealName(String memberRealName)
    {
        this.memberRealName = memberRealName;
    }

    public String getMemberIdNumber()
    {
        return memberIdNumber;
    }

    public void setMemberIdNumber(String memberIdNumber)
    {
        this.memberIdNumber = memberIdNumber;
    }

    public boolean isChecked()
    {
        return isChecked;
    }

    public void setChecked(boolean checked)
    {
        isChecked = checked;
    }

    @Override
    public int describeContents()
    {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.memberRealName);
        dest.writeString(this.memberIdNumber);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public static final Creator<MemberBean> CREATOR = new Creator<MemberBean>()
    {
        @Override
        public MemberBean createFromParcel(Parcel source)
        {
            return new MemberBean(source);
        }

        @Override
        public MemberBean[] newArray(int size)
        {
            return new MemberBean[size];
        }
    };
}

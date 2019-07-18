package com.example.trainticket_01.model.bean;

import android.os.Parcel;

import java.util.List;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 联系人列表实体类
 */

public class MemberListBean extends ResultBean
{
    private List<MemberBean> members;

    public List<MemberBean> getMembers()
    {
        return members;
    }

    public void setMembers(List<MemberBean> members)
    {
        this.members = members;
    }

    public MemberListBean()
    {
    }

    protected MemberListBean(Parcel source)
    {
        super(source);
        this.members = source.createTypedArrayList(MemberBean.CREATOR);
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
        dest.writeTypedList(this.members);
    }

    public static final Creator<MemberListBean> CREATOR = new Creator<MemberListBean>()
    {
        @Override
        public MemberListBean createFromParcel(Parcel source)
        {
            return new MemberListBean(source);
        }

        @Override
        public MemberListBean[] newArray(int size)
        {
            return new MemberListBean[size];
        }
    };
}

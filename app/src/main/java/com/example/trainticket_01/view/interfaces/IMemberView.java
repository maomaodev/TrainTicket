package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.MemberBean;

import java.util.List;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 联系人查询View接口
 */

public interface IMemberView
{
    /**
     * 查询联系人
     */
    void getMember();

    /**
     * 设置联系人列表
     *
     * @param members
     */
    void setMemberList(List<MemberBean> members);
}

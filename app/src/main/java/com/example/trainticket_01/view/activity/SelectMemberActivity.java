package com.example.trainticket_01.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.adapter.CommonAdapter;
import com.example.cookie_library.adapter.CommonViewHolder;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.MemberBean;
import com.example.trainticket_01.model.bean.MemberListBean;
import com.example.trainticket_01.model.bean.UserBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 选择联系人页
 */

@ContentView(R.layout.activity_select_member)
public class SelectMemberActivity extends BaseActivity
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.member_list)
    private ListView memberList;
    @ViewInject(R.id.finish_btn)
    private TextView finishBtn;

    private List<MemberBean> memberBeanList = new ArrayList<>();

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.select_member_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        getMember();
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.finish_btn:
                selectFinish();
                break;
            default:
                break;
        }
    }

    private void selectFinish()
    {
        List<MemberBean> memberSelected = new ArrayList<>();
        for(int i = 0; i < memberBeanList.size(); i++)
            if(memberBeanList.get(i).isChecked())
                memberSelected.add(memberBeanList.get(i));
        MemberListBean memberListBean = new MemberListBean();
        memberListBean.setMembers(memberSelected);

        Bundle bundle = new Bundle();
        bundle.putParcelable("member", memberListBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void getMember()
    {
        ProgressDialogUtils.showProgress(this);
        UserBean userBean = TrainTicketApplication.getUser();
        MemberBean bean = new MemberBean();
        bean.setUserId(userBean.getUserId());
        bean.setMemberIdNumber(userBean.getIdNumber());
        bean.setMemberRealName(userBean.getRealName());
        ArrayList<MemberBean> beanArrayList = new ArrayList<>();
        beanArrayList.add(bean);
        setMemberList(beanArrayList);
    }

    public void setMemberList(final List<MemberBean> members)
    {
        ProgressDialogUtils.hideProgress();
        CommonAdapter<MemberBean> adapter = new CommonAdapter<MemberBean>(this,
                R.layout.view_select_member_list_item, members)
        {
            @Override
            public void convert(final CommonViewHolder holder, MemberBean memberBean)
            {
                holder.setText(R.id.real_name_tv, memberBean.getMemberRealName());
                String idNumber = memberBean.getMemberIdNumber();
                idNumber = idNumber.substring(0, 4) + "**********" + idNumber.substring(14, 18);
                holder.setText(R.id.id_number_tv, idNumber);
                CheckBox checkBox = holder.getView(R.id.checkbox);
                checkBox.setChecked(memberBean.isChecked());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        members.get(holder.getPosition()).setChecked(checked);
                        memberBeanList = members;
                    }
                });
            }
        };

        memberList.setAdapter(adapter); // ListView设置适配器
    }
}

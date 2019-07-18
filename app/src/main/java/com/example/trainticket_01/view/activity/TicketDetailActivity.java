package com.example.trainticket_01.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.adapter.CommonAdapter;
import com.example.cookie_library.adapter.CommonViewHolder;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.MemberBean;
import com.example.trainticket_01.model.bean.MemberListBean;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.model.bean.TicketBean;
import com.example.trainticket_01.presenter.TicketDetailPresenter;
import com.example.trainticket_01.view.interfaces.ITicketDetailView;
import com.example.trainticket_01.widget.MeasuredListView;
import com.example.trainticket_01.widget.RadioGroupEx;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maomao on 2019/4/8.
 * <p>
 * 车票详情页
 */

@ContentView(R.layout.activity_ticket_detail)
public class TicketDetailActivity extends BaseActivity implements ITicketDetailView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.start_station_tv)
    private TextView startStationTv;
    @ViewInject(R.id.start_time_tv)
    private TextView startTimeTv;
    @ViewInject(R.id.train_no_tv)
    private TextView trainNoTv;
    @ViewInject(R.id.take_time_tv)
    private TextView takeTimeTv;
    @ViewInject(R.id.end_station_tv)
    private TextView endStationTv;
    @ViewInject(R.id.end_time_tv)
    private TextView endTimeTv;
    @ViewInject(R.id.seat_rb)
    private RadioGroupEx seatRb;
    @ViewInject(R.id.zy_rb)
    private RadioButton zyRb;
    @ViewInject(R.id.ze_rb)
    private RadioButton zeRb;
    @ViewInject(R.id.rw_rb)
    private RadioButton rwRb;
    @ViewInject(R.id.yw_rb)
    private RadioButton ywRb;
    @ViewInject(R.id.yz_rb)
    private RadioButton yzRb;
    @ViewInject(R.id.wz_rb)
    private RadioButton wzRb;
    @ViewInject(R.id.add_member_btn)
    private LinearLayout addMemberBtn;
    @ViewInject(R.id.member_list)
    private MeasuredListView memberList;
    @ViewInject(R.id.submit_btn)
    private TextView submitBtn;

    private String ticketId;    // 车票ID
    private String seatType;    // 席别
    private String seatCount;   // 席别票数
    private String seatPrice;   // 票价
    private OrderBean oldOrder; // 改签订单
    private TicketBean ticketBean;  // 车票信息
    private MemberListBean memberListBean = new MemberListBean();
    private AlertDialog.Builder builder;    // 提示框

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        ticketBean = getIntent().getParcelableExtra("ticket_info");
        oldOrder = getIntent().getParcelableExtra("order");

        if (null != oldOrder)
        {
            MemberBean memberBean = new MemberBean();
            memberBean.setMemberRealName(oldOrder.getRealName());
            List<MemberBean> orderMember = new ArrayList<>();
            orderMember.add(memberBean);
            memberListBean.setMembers(orderMember);
            addMemberBtn.setVisibility(View.GONE);
        }

        titleTv.setText(ticketBean.getStartDate());
        leftBtn.setImageResource(R.drawable.ic_back_white);
        startStationTv.setText(ticketBean.getStartStationName());
        startTimeTv.setText(ticketBean.getStartTime());
        trainNoTv.setText(ticketBean.getShift());
        String[] time = ticketBean.getTime().split(":");
        takeTimeTv.setText(time[0] + "小时" + time[1] + "分钟");
        endStationTv.setText(ticketBean.getToStationName());
        endTimeTv.setText(ticketBean.getArriveTime());

        zyRb.setText(String.format(getResources().getString(R.string.zy_num_text),
                ticketBean.getZyNum()));
        zeRb.setText(String.format(getResources().getString(R.string.ze_num_text),
                ticketBean.getZeNum()));
        rwRb.setText(String.format(getResources().getString(R.string.rw_num_text),
                ticketBean.getRwNum()));
        ywRb.setText(String.format(getResources().getString(R.string.yw_num_text),
                ticketBean.getYwNum()));
        yzRb.setText(String.format(getResources().getString(R.string.yz_num_text),
                ticketBean.getYzNum()));
        wzRb.setText(String.format(getResources().getString(R.string.wz_num_text),
                ticketBean.getWzNum()));

        // 默认选中一等座，需要进行初始化
        seatType = AppConfig.SEAT_TYPE_ZY;
        seatCount = ticketBean.getZyNum();
        ticketId = ticketBean.getZyTicketId();
        seatPrice = ticketBean.getZyPrice();

        leftBtn.setOnClickListener(this);
        addMemberBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        builder = new AlertDialog.Builder(this);
        seatRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setSeat(group.getCheckedRadioButtonId());
            }
        });
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.add_member_btn:
                turnForResult(SelectMemberActivity.class, 0);
                break;
            case R.id.submit_btn:
                doOrderTicket();
                break;
            default:
                break;
        }
    }

    @Override
    public void doOrderTicket()
    {
        if (null == memberListBean.getMembers() || 0 == memberListBean.getMembers().size())
            ToastUtils.show(this, R.string.select_member_tip);
        else
        {
            if (!seatCount.equals("-") && !seatCount.equals("0") && !TextUtils.isEmpty(ticketId))
            {
                // 拼装车票信息提示
                String message = "当前所选车次为" + ticketBean.getStartDate() + " " + ticketBean
                        .getStartTime() + "发出的" + ticketBean.getShift() + "次列车。您选择的是"
                        + seatType + "类型座位，车票价格为" + seatPrice + "元。";
                if (null != oldOrder && !TextUtils.isEmpty(oldOrder.getPrice()))
                    message += "\n改签前的车票费用" + oldOrder.getPrice() + "元将原路退回。";

                builder.setMessage(message);
                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ProgressDialogUtils.showProgress(TicketDetailActivity.this,
                                R.string.generate_order_tip);
                        new TicketDetailPresenter(TicketDetailActivity.this).doOrderTicket
                                (oldOrder, submitOrder());
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
            }
            else
            {
                builder.setMessage(R.string.seat_none_tip);
                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
            }

            builder.create().show();
        }
    }

    @Override
    public void orderSuccess(OrderListBean orderListBean)
    {
        ProgressDialogUtils.hideProgress();
        Bundle bundle = new Bundle();
        bundle.putParcelable("orderList", orderListBean);
        turnThenFinish(PayTicketActivity.class, bundle);
    }

    @Override
    public void orderFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0)
        {
            memberListBean = data.getExtras().getParcelable("member");
            CommonAdapter<MemberBean> memberAdapter = new CommonAdapter<MemberBean>(this,
                    R.layout.view_ticket_member_list_item, memberListBean.getMembers())
            {
                @Override
                public void convert(final CommonViewHolder holder, MemberBean memberBean)
                {
                    holder.setText(R.id.real_name_tv, TrainTicketApplication.getUser()
                            .getRealName());
                }
            };
            // ListView设置适配器
            memberList.setAdapter(memberAdapter);
        }
    }

    private OrderBean submitOrder()
    {
        OrderBean orderBean = new OrderBean();
        orderBean.setAccount(TrainTicketApplication.getUser().getAccount());
        orderBean.setUserId(TrainTicketApplication.getUser().getUserId());
        orderBean.setTicketId(ticketId);
        orderBean.setShift(ticketBean.getShift());
        orderBean.setFromStation(ticketBean.getStartStationName());
        orderBean.setStartTime(ticketBean.getStartTime());
        orderBean.setToStation(ticketBean.getToStationName());
        orderBean.setEndTime(ticketBean.getArriveTime());
        orderBean.setDate(ticketBean.getStartDate());
        orderBean.setTicketType(seatType);
        orderBean.setPrice(seatPrice);
        return orderBean;
    }

    private void setSeat(int radioButtonId)
    {
        switch (radioButtonId)
        {
            case R.id.zy_rb:
                seatType = AppConfig.SEAT_TYPE_ZY;
                seatCount = ticketBean.getZyNum();
                ticketId = ticketBean.getZyTicketId();
                seatPrice = ticketBean.getZyPrice();
                break;
            case R.id.ze_rb:
                seatType = AppConfig.SEAT_TYPE_ZE;
                seatCount = ticketBean.getZeNum();
                ticketId = ticketBean.getZeTicketId();
                seatPrice = ticketBean.getZePrice();
                break;
            case R.id.rw_rb:
                seatType = AppConfig.SEAT_TYPE_RW;
                seatCount = ticketBean.getRwNum();
                ticketId = ticketBean.getRwTicketId();
                seatPrice = ticketBean.getRwPrice();
                break;
            case R.id.yw_rb:
                seatType = AppConfig.SEAT_TYPE_YW;
                seatCount = ticketBean.getYwNum();
                ticketId = ticketBean.getYwTicketId();
                seatPrice = ticketBean.getYwPrice();
                break;
            case R.id.yz_rb:
                seatType = AppConfig.SEAT_TYPE_YZ;
                seatCount = ticketBean.getYzNum();
                ticketId = ticketBean.getYzTicketId();
                seatPrice = ticketBean.getYzPrice();
                break;
            case R.id.wz_rb:
                seatType = AppConfig.SEAT_TYPE_WZ;
                seatCount = ticketBean.getWzNum();
                ticketId = ticketBean.getWzTicketId();
                seatPrice = ticketBean.getWzPrice();
                break;
            default:
                break;
        }
    }
}

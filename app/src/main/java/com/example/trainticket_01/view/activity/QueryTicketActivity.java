package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.adapter.CommonAdapter;
import com.example.cookie_library.adapter.CommonViewHolder;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.TicketBean;
import com.example.trainticket_01.model.bean.TicketListBean;
import com.example.trainticket_01.presenter.QueryTicketPresenter;
import com.example.trainticket_01.view.interfaces.IQueryTicketView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/7.
 * <p>
 * 车票查询页
 */

@ContentView(R.layout.activity_query_ticket)
public class QueryTicketActivity extends BaseActivity implements IQueryTicketView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.right_btn)
    private ImageView rightBtn;
    @ViewInject(R.id.ticket_list)
    private ListView ticketList;

    private String startStation;    // 出发站点
    private String endStation;  // 目的站点
    private String startDate;   // 出发时间
    private String trainType;   // 火车类型
    private OrderBean oldOrder; // 改签订单

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        // 获取前面页面传递过来的数据
        startStation = getIntent().getStringExtra("startStation");
        endStation = getIntent().getStringExtra("endStation");
        startDate = getIntent().getStringExtra("startDate");
        trainType = getIntent().getStringExtra("trainType");
        oldOrder = getIntent().getParcelableExtra("order");

        if(null != oldOrder)
        {
            startStation = oldOrder.getFromStation();
            endStation = oldOrder.getToStation();
            startDate = oldOrder.getDate();
        }

        titleTv.setText(startDate);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        rightBtn.setImageResource(R.drawable.ic_cached_white);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        doQueryTicket(startStation, endStation, startDate, trainType);
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:
                doQueryTicket(startStation, endStation, startDate, trainType);
                break;
            default:
                break;
        }
    }

    @Override
    public void doQueryTicket(String from, String to, String date, String model)
    {
        ProgressDialogUtils.showProgress(this);
        new QueryTicketPresenter(this).doQueryTicket(from, to, date, model);
    }

    @Override
    public void querySuccess(final TicketListBean ticketListBean)
    {
        ProgressDialogUtils.hideProgress();
        // 配置数据到适配器
        CommonAdapter<TicketBean> adapter = new CommonAdapter<TicketBean>(this, R.layout
                .view_ticket_list_item, ticketListBean.getTickets())
        {
            @Override
            public void convert(CommonViewHolder holder, TicketBean ticketBean)
            {
                // 为item的每个字段设置值
                holder.setText(R.id.train_no_tv, ticketBean.getShift());
                holder.setText(R.id.start_station_tv, ticketBean.getStartStationName());
                holder.setText(R.id.start_time_tv, ticketBean.getStartTime());
                holder.setText(R.id.end_station_tv, ticketBean.getToStationName());
                holder.setText(R.id.end_time_tv, ticketBean.getArriveTime());
                String[] times = ticketBean.getTime().split(":");
                holder.setText(R.id.take_time_tv, times[0] + "小时" + times[1] + "分钟");
            }
        };

        // ListView设置适配器，并监听点击事件
        ticketList.setAdapter(adapter);
        ticketList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelable("ticket_info", ticketListBean.getTickets().get(position));
                bundle.putParcelable("order", oldOrder);
                turn(TicketDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void queryFailed()
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, R.string.query_failed_text);
    }
}
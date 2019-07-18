package com.example.trainticket_01.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.adapter.CommonAdapter;
import com.example.cookie_library.adapter.CommonViewHolder;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.presenter.QueryOrderPresenter;
import com.example.trainticket_01.view.interfaces.IQueryOrderView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maomao on 2019/4/11.
 */

@ContentView(R.layout.activity_order)
public class QueryOrderActivity extends BaseActivity implements IQueryOrderView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.refresh_swipe)
    private SwipeRefreshLayout refreshSwipe;
    @ViewInject(R.id.order_list_lv)
    private ListView orderListLv;

    private int ORDER_STATUS;   // 订单状态
    private OrderAdapter orderAdapter;  // 适配器
    private List<OrderBean> orderList = new ArrayList<>();  // 订单列表

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        ORDER_STATUS = getIntent().getIntExtra("order_status", AppConfig.ORDER_NOW);
        setTitle(); // 设置标题
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        refreshSwipe.setColorSchemeResources(R.color.colorPrimary);
        refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                doQueryOrder();
                refreshSwipe.setRefreshing(false);
            }
        });

        orderAdapter = new OrderAdapter(this, orderList);
        orderListLv.setAdapter(orderAdapter);
        setOrderListListener(); // 设置监听事件
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        doQueryOrder();  // 获取订单列表
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 根据订单状态设置标题
     */
    private void setTitle()
    {
        switch (ORDER_STATUS)
        {
            case AppConfig.ORDER_UNPAID:
                titleTv.setText(R.string.order_unpaid);
                break;
            case AppConfig.ORDER_NOW:
                titleTv.setText(R.string.order_now);
                break;
            case AppConfig.ORDER_OLD:
                titleTv.setText(R.string.order_old);
                break;
            default:
                break;
        }
    }

    /**
     * 根据订单状态设置监听事件
     */
    private void setOrderListListener()
    {
        if (ORDER_STATUS == AppConfig.ORDER_UNPAID)
        {
            orderListLv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    OrderBean orderBean = orderList.get(position);
                    List<OrderBean> orders = new ArrayList<>();
                    orders.add(orderBean);
                    OrderListBean orderListBean = new OrderListBean();
                    orderListBean.setOrders(orders);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("orderList", orderListBean);
                    turn(PayTicketActivity.class, bundle);
                }
            });
        }
        else
        {
            orderListLv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    OrderBean orderBean = orderList.get(position);
                    List<OrderBean> orders = new ArrayList<>();
                    orders.add(orderBean);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("order", orderBean);
                    turn(OrderDetailActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void doQueryOrder()
    {
        orderList.clear();  // 每次查询前清除原有数据
        ProgressDialogUtils.showProgress(this);
        new QueryOrderPresenter(this).doQueryOrder(TrainTicketApplication.getUser(), ORDER_STATUS);
    }

    @Override
    public void querySuccess(List<OrderBean> orders)
    {
        ProgressDialogUtils.hideProgress();
        orderList.addAll(orders);
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }

    /**
     * 订单列表适配器
     */
    class OrderAdapter extends CommonAdapter<OrderBean>
    {
        public OrderAdapter(Context context, List<OrderBean> data)
        {
            super(context, R.layout.view_order_list_item, data);
        }

        @Override
        public void convert(CommonViewHolder holder, final OrderBean orderBean)
        {
            // 设置每项字段值
            holder.setText(R.id.date_tv, orderBean.getDate());
            holder.setText(R.id.start_time_tv, orderBean.getStartTime() + "开");
            holder.setText(R.id.start_station_tv, orderBean.getFromStation());
            holder.setText(R.id.end_station_tv, orderBean.getToStation());
            holder.setText(R.id.train_no_tv, orderBean.getShift());
            holder.setText(R.id.seat_type_tv, orderBean.getTicketType());
            holder.setText(R.id.real_name_tv, orderBean.getRealName());
            holder.setText(R.id.user_type_tv, TrainTicketApplication.getUser().getUserType());
            holder.setText(R.id.price_tv, orderBean.getPrice() + "元");

            String carriage_and_seat = orderBean.getCarriage();
            if (carriage_and_seat.contains("-"))
            {
                holder.setText(R.id.carriage_tv, carriage_and_seat.split("-")[0] + "号车");
                holder.setText(R.id.seat_no_tv, carriage_and_seat.split("-")[1] + "号座");
            }
            else
            {
                holder.setText(R.id.carriage_tv, "无座");
                holder.setText(R.id.seat_no_tv, "无座");
            }

            // 退票改签按钮区域的显示和隐藏
            LinearLayout alterTicketLl = holder.getView(R.id.alter_ticket_ll);
            alterTicketLl.setVisibility(View.GONE);
            if (ORDER_STATUS == AppConfig.ORDER_NOW)
            {
                alterTicketLl.setVisibility(View.VISIBLE);
                TextView returnTicketBtn = holder.getView(R.id.return_ticket_btn);
                TextView changeTicketBtn = holder.getView(R.id.change_ticket_btn);

                returnTicketBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("order", orderBean);
                        turn(ReturnTicketActivity.class, bundle);
                    }
                });
                changeTicketBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("order", orderBean);
                        turn(QueryTicketActivity.class, bundle);
                    }
                });
            }
        }
    }

}

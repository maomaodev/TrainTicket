package com.example.trainticket_01.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cookie_library.fragment.BaseFragment;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.utils.DateTimeUtils;
import com.example.trainticket_01.view.activity.QueryOrderActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/4.
 *
 * 订单碎片
 */

@ContentView(R.layout.fragment_order)
public class OrderFragment extends BaseFragment
{
    @ViewInject(R.id.date_tv)
    private TextView dateTv;
    @ViewInject(R.id.un_pay_order_btn)
    private TextView unPayOrderBtn;
    @ViewInject(R.id.now_order_btn)
    private TextView nowOrderBtn;
    @ViewInject(R.id.old_order_btn)
    private TextView oldOrderBtn;


    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        dateTv.setText(DateTimeUtils.getInstance().getCurrentDate());
        unPayOrderBtn.setOnClickListener(this);
        nowOrderBtn.setOnClickListener(this);
        oldOrderBtn.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view)
    {
        Bundle bundle = new Bundle();
        switch (view.getId())
        {
            case R.id.un_pay_order_btn:
                bundle.putInt("order_status", AppConfig.ORDER_UNPAID);
                break;
            case R.id.now_order_btn:
                bundle.putInt("order_status", AppConfig.ORDER_NOW);
                break;
            case R.id.old_order_btn:
                bundle.putInt("order_status", AppConfig.ORDER_OLD);
                break;
            default:
                break;
        }
        turn(QueryOrderActivity.class, bundle);
    }
}
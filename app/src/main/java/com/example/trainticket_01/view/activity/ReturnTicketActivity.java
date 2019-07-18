package com.example.trainticket_01.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.presenter.ReturnTicketPresenter;
import com.example.trainticket_01.view.interfaces.IReturnTicketView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/11.
 */

@ContentView(R.layout.activity_return_ticket)
public class ReturnTicketActivity extends BaseActivity implements IReturnTicketView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.date_tv)
    private TextView dateTv;
    @ViewInject(R.id.start_time_tv)
    private TextView startTimeTv;
    @ViewInject(R.id.start_station_tv)
    private TextView startStationTv;
    @ViewInject(R.id.end_station_tv)
    private TextView endStationTv;
    @ViewInject(R.id.train_no_tv)
    private TextView trainNoTv;
    @ViewInject(R.id.seat_type_tv)
    private TextView seatTypeTv;
    @ViewInject(R.id.carriage_tv)
    private TextView carriageTv;
    @ViewInject(R.id.seat_no_tv)
    private TextView seatNoTv;
    @ViewInject(R.id.real_name_tv)
    private TextView realNameTv;
    @ViewInject(R.id.user_type_tv)
    private TextView userTypeTv;
    @ViewInject(R.id.price_tv)
    private TextView priceTv;
    @ViewInject(R.id.return_ticket_btn)
    private TextView returnTicketBtn;

    private OrderBean orderBean;    // 退票订单实体

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.return_ticket_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        returnTicketBtn.setOnClickListener(this);

        orderBean = getIntent().getExtras().getParcelable("order");
        dateTv.setText(orderBean.getDate());
        startTimeTv.setText(orderBean.getStartTime() + "开");
        startStationTv.setText(orderBean.getFromStation());
        endStationTv.setText(orderBean.getToStation());
        trainNoTv.setText(orderBean.getShift());
        seatTypeTv.setText(orderBean.getTicketType());
        realNameTv.setText(orderBean.getRealName());
        userTypeTv.setText(orderBean.getUserType());
        priceTv.setText(orderBean.getPrice() + "元");
        String carriage_and_seat = orderBean.getCarriage();
        if(carriage_and_seat.contains("-"))
        {
            carriageTv.setText(carriage_and_seat.split("-")[0] + "号车");
            seatNoTv.setText(carriage_and_seat.split("-")[1] + "号座");
        }
        else
        {
            carriageTv.setText("无座");
            seatNoTv.setText("无座");
        }
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.return_ticket_btn:
                doReturnTicket();
                break;
            default:
                break;
        }
    }

    @Override
    public void doReturnTicket()
    {
        ProgressDialogUtils.showProgress(this);
        new ReturnTicketPresenter(this).doReturnTicket(orderBean);
    }

    @Override
    public void returnSuccess(String msg)
    {
        ProgressDialogUtils.hideProgress();
        String message = "退票成功，车票费用" + orderBean.getPrice() + "元将原路退回。";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void returnFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }
}

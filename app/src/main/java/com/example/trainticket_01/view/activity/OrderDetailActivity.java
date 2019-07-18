package com.example.trainticket_01.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.utils.QRCodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/12.
 * <p>
 * 订单详情页
 */

@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends BaseActivity
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
    @ViewInject(R.id.date_tv)
    private TextView dateTv;
    @ViewInject(R.id.end_station_tv)
    private TextView endStationTv;
    @ViewInject(R.id.end_time_tv)
    private TextView endTimeTv;
    @ViewInject(R.id.real_name_tv)
    private TextView realNameTv;
    @ViewInject(R.id.id_number_tv)
    private TextView idNumberTv;
    @ViewInject(R.id.seat_type_tv)
    private TextView seatTypeTv;
    @ViewInject(R.id.seat_no_tv)
    private TextView seatNoTv;
    @ViewInject(R.id.price_tv)
    private TextView priceTv;
    @ViewInject(R.id.carriage_tv)
    private TextView carriageTv;
    @ViewInject(R.id.qr_code_iv)
    private ImageView qrCodeIv;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.tip_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);

        OrderBean orderBean = getIntent().getParcelableExtra("order");
        startStationTv.setText(orderBean.getFromStation());
        startTimeTv.setText(orderBean.getStartTime());
        trainNoTv.setText(orderBean.getShift());
        dateTv.setText(orderBean.getDate());
        endStationTv.setText(orderBean.getToStation());
        endTimeTv.setText(orderBean.getEndTime());
        realNameTv.setText(orderBean.getRealName());
        String idNumber = TrainTicketApplication.getUser().getIdNumber();
        idNumber = idNumber.substring(0, 4) + "**********" + idNumber.substring(14, 18);
        idNumberTv.setText(idNumber);
        seatTypeTv.setText(orderBean.getTicketType());
        priceTv.setText("￥" + orderBean.getPrice());
        String carriage_and_seat = orderBean.getCarriage();
        if(carriage_and_seat.contains("-"))
        {
            carriageTv.setText(carriage_and_seat.split("-")[0] + "号车");
            seatNoTv.setText(carriage_and_seat.split("-")[1] + "号座");
        }

        createQRCode(orderBean);
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

    private void createQRCode(OrderBean bean)
    {
        String content = bean.getUserId() + " " + bean.getTicketId();
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap qrCode = QRCodeUtils.createQRCodeBitmap(content, qrCodeIv.getLayoutParams().width,
                logo, 0.2f);
        qrCodeIv.setImageBitmap(qrCode);
    }
}

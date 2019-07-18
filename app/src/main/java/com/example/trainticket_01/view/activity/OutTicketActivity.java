package com.example.trainticket_01.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.trainticket_01.R;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.utils.QRCodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/10.
 * <p>
 * 出票页
 */

@ContentView(R.layout.activity_out_ticket)
public class OutTicketActivity extends BaseActivity
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.ticket_info)
    private LinearLayout ticketInfo;
    @ViewInject(R.id.date_tv)
    private TextView dateTv;
    @ViewInject(R.id.start_time_tv)
    private TextView startTimeTv;
    @ViewInject(R.id.start_station_tv)
    private TextView startStationTv;
    @ViewInject(R.id.end_station_tv)
    private TextView endStationTv;
    @ViewInject(R.id.carriage_tv)
    private TextView carriageTv;
    @ViewInject(R.id.seat_no_tv)
    private TextView seatNoTv;
    @ViewInject(R.id.seat_type_tv)
    private TextView seatTypeTv;
    @ViewInject(R.id.price_tv)
    private TextView priceTv;
    @ViewInject(R.id.qr_code_iv)
    private ImageView qrCodeIv;


    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.tip_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);

        OrderBean orderBean = getIntent().getParcelableExtra("order");
        dateTv.setText(orderBean.getDate());
        startTimeTv.setText(orderBean.getStartTime());
        startStationTv.setText(orderBean.getFromStation());
        endStationTv.setText(orderBean.getToStation());
        carriageTv.setText(orderBean.getCarriage());
        seatNoTv.setText(orderBean.getSeat());
        seatTypeTv.setText(orderBean.getTicketType());
        priceTv.setText("￥" + orderBean.getPrice());

        String ticketId = orderBean.getTicketId();
        String userId = orderBean.getUserId();
        createQRCode(ticketId, userId);
        startAnimation();
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
     * 使用车票和用户ID生成出行二维码
     *
     * @param ticketId 车票ID
     * @param userId   用户ID
     */
    private void createQRCode(String ticketId, String userId)
    {
        String content = userId + " " + ticketId;   // 二维码存储的内容
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap qrCode = QRCodeUtils.createQRCodeBitmap(content,
                qrCodeIv.getLayoutParams().width, logo, 0.2f);
        qrCodeIv.setImageBitmap(qrCode);
    }

    /**
     * 开始出票动画
     */
    private void startAnimation()
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_down);
        anim.setFillAfter(true);
        ticketInfo.startAnimation(anim);
    }
}

package com.example.trainticket_01.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.presenter.PayTicketPresenter;
import com.example.trainticket_01.view.interfaces.IPayTicketView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/10.
 * <p>
 * 车票支付页
 */

@ContentView(R.layout.activity_pay_ticket)
public class PayTicketActivity extends BaseActivity implements IPayTicketView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.should_pay_tv)
    private TextView shouldPayTv;
    @ViewInject(R.id.pay_method_rg)
    private RadioGroup payMethodRg;
    @ViewInject(R.id.gs_bank_rb)
    private RadioButton gsBankRb;
    @ViewInject(R.id.submit_btn)
    private TextView submitBtn;

    private String price;   // 应付金额
    private String payMethod;   // 支付方式
    private OrderListBean orderListBean;    // 订单列表

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.pay_ticket_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        orderListBean = getIntent().getParcelableExtra("orderList");
        price = orderListBean.getOrders().get(0).getPrice();
        shouldPayTv.setText(String.format(getResources().getString(R.string.should_pay_text),
                price));

        payMethodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton = (RadioButton) findViewById(group
                        .getCheckedRadioButtonId());
                payMethod = radioButton.getText().toString();
            }
        });
        gsBankRb.setChecked(true);  // 设置默认选中项，必须在监听器后
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.submit_btn:
                doPayTicket();
                break;
            default:
                break;
        }
    }


    @Override
    public void doPayTicket()
    {
        String message = "应付金额为" + price + "元，使用" + payMethod + "支付，是否确认支付购票？";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                ProgressDialogUtils.showProgress(PayTicketActivity.this, "支付中...");
                new PayTicketPresenter(PayTicketActivity.this).doPayTicket(orderListBean);
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

        builder.create().show();
    }

    @Override
    public void paySuccess(OrderListBean orderListBean)
    {
        ProgressDialogUtils.hideProgress();
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", orderListBean.getOrders().get(0));

        turnThenFinish(OutTicketActivity.class, bundle);
    }

    @Override
    public void payFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }
}

package com.example.trainticket_01.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.presenter.ModifyPasswordPresenter;
import com.example.trainticket_01.view.interfaces.IModifyPasswordView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 修改密码页
 */

@ContentView(R.layout.activity_modify_password)
public class ModifyPasswordActivity extends BaseActivity implements IModifyPasswordView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.old_password_et)
    private EditText oldPasswordEt;
    @ViewInject(R.id.new_password_et)
    private EditText newPasswordEt;
    @ViewInject(R.id.re_new_password_et)
    private EditText reNewPasswordEt;
    @ViewInject(R.id.submit_btn)
    private TextView submitBtn;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.modify_password);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
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
                doModifyPassword();
                break;
            default:
                break;
        }
    }

    @Override
    public void doModifyPassword()
    {
        String oldPassword = oldPasswordEt.getText().toString();
        String newPassword = newPasswordEt.getText().toString();
        String reNewPassword = reNewPasswordEt.getText().toString();

        if (TextUtils.isEmpty(oldPassword))
            ToastUtils.show(this, R.string.old_password_hint);
        else if (TextUtils.isEmpty(newPassword))
            ToastUtils.show(this, R.string.new_password_hint);
        else if (TextUtils.isEmpty(reNewPassword))
            ToastUtils.show(this, R.string.re_new_password_hint);
        else if (!reNewPassword.equals(newPassword))
            ToastUtils.show(this, R.string.re_password_error_hint);
        else
        {
            ProgressDialogUtils.showProgress(this);
            UserBean userBean = new UserBean();
            userBean.setAccount(TrainTicketApplication.getUser().getAccount());
            userBean.setPassword(oldPassword);
            new ModifyPasswordPresenter(this).doModifyPassword(userBean, newPassword);
        }
    }

    @Override
    public void modifySuccess(UserBean userBean)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, userBean.getResMsg());
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void modifyFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }
}

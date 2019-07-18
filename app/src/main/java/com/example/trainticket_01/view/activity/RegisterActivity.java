package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.presenter.RegisterPresenter;
import com.example.trainticket_01.view.interfaces.IRegisterView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 注册
 */

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements IRegisterView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.account_et)
    private EditText accountEt;
    @ViewInject(R.id.password_et)
    private EditText passwordEt;
    @ViewInject(R.id.re_password_et)
    private EditText rePasswordEt;
    @ViewInject(R.id.real_name_et)
    private EditText realNameEt;
    @ViewInject(R.id.id_number_et)
    private EditText idNumberEt;
    @ViewInject(R.id.register_btn)
    private TextView registerBtn;
    @ViewInject(R.id.adult_type_rb)
    private RadioButton adultTypeRb;
    @ViewInject(R.id.student_type_rb)
    private RadioButton studentTypeRb;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.register_text);    // 设置标题
        leftBtn.setImageResource(R.drawable.ic_back_white);   // 设置返回键
        leftBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            case R.id.register_btn:
                doRegister();
                break;
            default:
                break;
        }
    }

    @Override
    public void doRegister()
    {
        String account = accountEt.getText().toString();
        String password = passwordEt.getText().toString();
        String rePassword = rePasswordEt.getText().toString();
        String realName = realNameEt.getText().toString();
        String idNumber = idNumberEt.getText().toString();

        if (TextUtils.isEmpty(account))
            ToastUtils.show(this, R.string.account_hint);
        else if (TextUtils.isEmpty(password))
            ToastUtils.show(this, R.string.password_hint);
        else if (TextUtils.isEmpty(rePassword))
            ToastUtils.show(this, R.string.re_password_hint);
        else if (!rePassword.equals(password))
            ToastUtils.show(this, R.string.re_password_error_hint);
        else if (TextUtils.isEmpty(realName))
            ToastUtils.show(this, R.string.real_name_hint);
        else if (TextUtils.isEmpty(idNumber))
            ToastUtils.show(this, R.string.id_number_hint);
        else if (idNumber.length() != 18)
            ToastUtils.show(this, R.string.id_number_error_hint);
        else
        {
            ProgressDialogUtils.showProgress(this);
            UserBean userBean = new UserBean();
            userBean.setAccount(account);
            userBean.setPassword(password);
            userBean.setRealName(realName);
            userBean.setIdNumber(idNumber);
            if (adultTypeRb.isChecked())
                userBean.setUserType(getResources().getString(R.string.user_type_adult));
            else if (studentTypeRb.isChecked())
                userBean.setUserType(getResources().getString(R.string.user_type_student));
            new RegisterPresenter(this).doRegister(userBean);
        }
    }

    @Override
    public void registerSuccess(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
        finish();
    }

    @Override
    public void registerFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }
}

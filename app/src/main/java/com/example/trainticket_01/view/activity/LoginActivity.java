package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.utils.KeyBoardUtils;
import com.example.cookie_library.utils.ProgressDialogUtils;
import com.example.cookie_library.utils.SharedPreferencesUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.presenter.LoginPresenter;
import com.example.trainticket_01.view.interfaces.ILoginView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/2.
 * <p>
 * 登录
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ILoginView
{
    @ViewInject(R.id.account_et)
    private EditText accountEt;
    @ViewInject(R.id.password_et)
    private EditText passwordEt;
    @ViewInject(R.id.login_btn)
    private TextView loginBtn;
    @ViewInject(R.id.auto_login_cb)
    private CheckBox autoLoginCb;
    @ViewInject(R.id.register_btn)
    private TextView registerBtn;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        // 设置按钮监听事件
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        // 检测自动登录
        boolean isRemember = (boolean) SharedPreferencesUtils.get(this, "isRemember", false);
        if (isRemember)
            autoLogin();

        // 设置自动登录
        autoLoginCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                    SharedPreferencesUtils.put(LoginActivity.this, "isRemember", true);
                else
                    SharedPreferencesUtils.put(LoginActivity.this, "isRemember", false);
            }
        });
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.register_btn:
                turn(RegisterActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void doLogin()
    {
        String account = accountEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(account))
            ToastUtils.show(this, R.string.account_hint);
        else if (TextUtils.isEmpty(password))
            ToastUtils.show(this, R.string.password_hint);
        else
        {
            ProgressDialogUtils.showProgress(this);
            UserBean userBean = new UserBean();
            userBean.setAccount(account);
            userBean.setPassword(password);
            new LoginPresenter(this).doLogin(userBean);
        }
    }

    @Override
    public void autoLogin()
    {
        ProgressDialogUtils.showProgress(this);
        UserBean userBean = new UserBean();
        String account = (String) SharedPreferencesUtils.get(this, "account", "");
        String password = (String) SharedPreferencesUtils.get(this, "password", "");
        userBean.setAccount(account);
        userBean.setPassword(password);
        new LoginPresenter(this).doLogin(userBean);
    }

    @Override
    public void loginSuccess(UserBean userBean)
    {
        ProgressDialogUtils.hideProgress();
        TrainTicketApplication.setUser(userBean);
        SharedPreferencesUtils.put(this, "account", userBean.getAccount());
        SharedPreferencesUtils.put(this, "password", userBean.getPassword());
        turnThenFinish(MainActivity.class);
        KeyBoardUtils.closeKeyboard(passwordEt, this);
    }

    @Override
    public void loginFailed(String msg)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, msg);
    }
}

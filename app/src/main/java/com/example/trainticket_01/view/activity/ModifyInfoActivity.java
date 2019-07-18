package com.example.trainticket_01.view.activity;

import android.app.Activity;
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
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.presenter.ModifyInfoPresenter;
import com.example.trainticket_01.view.interfaces.IModifyInfoView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/6.
 * <p>
 * 修改信息页
 */

@ContentView(R.layout.activity_modify_info)
public class ModifyInfoActivity extends BaseActivity implements IModifyInfoView
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.real_name_et)
    private EditText realNameEt;
    @ViewInject(R.id.id_number_et)
    private EditText idNumberEt;
    @ViewInject(R.id.adult_type_rb)
    private RadioButton adultTypeRb;
    @ViewInject(R.id.student_type_rb)
    private RadioButton studentTypeRb;
    @ViewInject(R.id.submit_btn)
    private TextView submitBtn;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.modify_info);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        UserBean userBean = TrainTicketApplication.getUser();
        realNameEt.setText(userBean.getRealName());
        realNameEt.setSelection(realNameEt.getText().length());
        idNumberEt.setText(userBean.getIdNumber());
        if (userBean.getUserType() != null)
        {
            if (userBean.getUserType().equals(getResources().getString(R.string.user_type_adult)))
                adultTypeRb.setChecked(true);
            else if (userBean.getUserType().equals(getResources().getString(R.string
                    .user_type_student)))
                studentTypeRb.setChecked(true);
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
            case R.id.submit_btn:
                doModifyInfo();
                break;
            default:
                break;
        }
    }

    @Override
    public void doModifyInfo()
    {
        String realName = realNameEt.getText().toString();
        String idNumber = idNumberEt.getText().toString();
        if (TextUtils.isEmpty(realName))
            ToastUtils.show(this, R.string.real_name_hint);
        else if (TextUtils.isEmpty(idNumber))
            ToastUtils.show(this, R.string.id_number_hint);
        else if (idNumber.length() != 18)
            ToastUtils.show(this, R.string.id_number_error_hint);
        else
        {
            ProgressDialogUtils.showProgress(this);
            UserBean userBean = new UserBean();
            userBean.setUserId(TrainTicketApplication.getUser().getUserId());
            userBean.setRealName(realName);
            userBean.setIdNumber(idNumber);
            if (adultTypeRb.isChecked())
                userBean.setUserType(getResources().getString(R.string.user_type_adult));
            else if (studentTypeRb.isChecked())
                userBean.setUserType(getResources().getString(R.string.user_type_student));
            new ModifyInfoPresenter(this).doModifyInfo(userBean);
        }
    }

    @Override
    public void modifySuccess(UserBean userBean)
    {
        ProgressDialogUtils.hideProgress();
        ToastUtils.show(this, userBean.getResMsg());
        // 为系统重新设置用户实体类
        TrainTicketApplication.setUser(userBean);
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

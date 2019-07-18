package com.example.trainticket_01.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cookie_library.fragment.BaseFragment;
import com.example.cookie_library.utils.SharedPreferencesUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.TrainTicketApplication;
import com.example.trainticket_01.view.activity.AboutActivity;
import com.example.trainticket_01.view.activity.LoginActivity;
import com.example.trainticket_01.view.activity.ModifyInfoActivity;
import com.example.trainticket_01.view.activity.ModifyPasswordActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * 用户碎片
 */

@ContentView(R.layout.fragment_me)
public class MeFragment extends BaseFragment
{
    private static final int MODIFY_INFO = 0;
    private static final int MODIFY_PASSWORD = 1;

    @ViewInject(R.id.real_name_tv)
    private TextView realNameTv;
    @ViewInject(R.id.id_number_tv)
    private TextView idNumberTv;
    @ViewInject(R.id.modify_info_btn)
    private TextView modifyInfoBtn;
    //    @ViewInject(R.id.modify_member_btn)
    //    private TextView modifyMemberBtn;
    @ViewInject(R.id.modify_password_btn)
    private TextView modifyPasswordBtn;
    @ViewInject(R.id.about_btn)
    private TextView aboutBtn;
    @ViewInject(R.id.exit_btn)
    private TextView exitBtn;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        // 设置按钮点击事件
        modifyInfoBtn.setOnClickListener(this);
        //        modifyMemberBtn.setOnClickListener(this);
        modifyPasswordBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        // 设置用户基本信息
        setUserInfo();
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.modify_info_btn:
                turnForResult(ModifyInfoActivity.class, MODIFY_INFO);
                break;
            //            case R.id.modify_member_btn:
            //                break;
            case R.id.modify_password_btn:
                turnForResult(ModifyPasswordActivity.class, MODIFY_PASSWORD);
                break;
            case R.id.about_btn:
                turn(AboutActivity.class);
                break;
            case R.id.exit_btn:
                exitLogin();
                break;
            default:
                break;
        }
    }

    /**
     * 注销，即退出登录
     */
    private void exitLogin()
    {
        String message = "是否确认退出？";
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                SharedPreferencesUtils.put(getContext(), "isRemember", false);
                turnThenFinish(LoginActivity.class);
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

    /**
     * 设置用户基本信息
     */
    private void setUserInfo()
    {
        realNameTv.setText(TrainTicketApplication.getUser().getRealName());
        String idNumber = TrainTicketApplication.getUser().getIdNumber();
        idNumber = idNumber.substring(0, 4) + "**********" + idNumber.substring(14, 18);
        idNumberTv.setText(idNumber);
    }

    /**
     * 处理从上一个活动中返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case MODIFY_INFO:
                    setUserInfo();  // 重新显示用户信息
                    break;
                case MODIFY_PASSWORD:
                    // 成功修改密码后，自动登录取消，并返回登录界面
                    SharedPreferencesUtils.put(getContext(), "isRemember", false);
                    turnThenFinish(LoginActivity.class);
                    break;
                default:
                    break;
            }
        }
    }
}

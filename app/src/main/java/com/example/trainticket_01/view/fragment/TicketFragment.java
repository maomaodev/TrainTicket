package com.example.trainticket_01.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cookie_library.fragment.BaseFragment;
import com.example.cookie_library.utils.DateTimePickUtils;
import com.example.cookie_library.utils.KeyBoardUtils;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.StationBean;
import com.example.trainticket_01.utils.DateTimeUtils;
import com.example.trainticket_01.view.activity.QueryTicketActivity;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * 购票碎片
 */

@ContentView(R.layout.fragment_ticket)
public class TicketFragment extends BaseFragment
{
    private String[] stations;  // 站点数组
    private String trainType = AppConfig.TRAIN_TYPE_A;

    @ViewInject(R.id.start_station_tv)
    private AutoCompleteTextView startStationTv;
    @ViewInject(R.id.end_station_tv)
    private AutoCompleteTextView endStationTv;
    @ViewInject(R.id.change_btn)
    private ImageView changeBtn;
    @ViewInject(R.id.date_tv)
    private TextView dateBtn;
    @ViewInject(R.id.model_rg)
    private RadioGroup modelRg;
    @ViewInject(R.id.query_btn)
    private TextView queryBtn;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        getStation();   // 获取站点信息,实现站点的自动提示功能
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_list_item_1, stations);
        startStationTv.setAdapter(adapter);
        endStationTv.setAdapter(adapter);

        startStationTv.setText("北京");
        endStationTv.setText("宝鸡");

        changeBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
        dateBtn.setText(DateTimeUtils.getInstance().getCurrentTime().substring(0, 10));

        // 设置火车类型选择监听器
        modelRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (group.getCheckedRadioButtonId())
                {
                    case R.id.model_all_rb:
                        trainType = AppConfig.TRAIN_TYPE_A;
                        break;
                    case R.id.model_g_rb:
                        trainType = AppConfig.TRAIN_TYPE_G;
                        break;
                    case R.id.model_t_rb:
                        trainType = AppConfig.TRAIN_TYPE_T;
                        break;
                    case R.id.model_k_rb:
                        trainType = AppConfig.TRAIN_TYPE_K;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.change_btn:
                changeStation();
                break;
            case R.id.date_tv:
                selectDate();
                break;
            case R.id.query_btn:
                queryTicket();
                break;
            default:
                break;
        }
    }

    /**
     * 始末站交换
     */
    private void changeStation()
    {
        // 取出始末站
        String tempStart = startStationTv.getText().toString();
        String tempEnd = endStationTv.getText().toString();
        // 替换始末站
        startStationTv.setText(tempEnd);
        endStationTv.setText(tempStart);
        // 软键盘消失
        KeyBoardUtils.closeKeyboard(startStationTv, getContext());
        KeyBoardUtils.closeKeyboard(endStationTv, getContext());
        // 清除焦点
        startStationTv.clearFocus();
        endStationTv.clearFocus();
    }

    /**
     * 选择出发日期
     */
    private void selectDate()
    {
        DateTimePickUtils dateAndTimePicker = new DateTimePickUtils(getActivity(),
                DateTimeUtils.getInstance().getCurrentTime());
        dateAndTimePicker.datePicKDialog(dateBtn);
    }

    /**
     * 获取站点信息
     */
    private void getStation()
    {
        try
        {
            // 读取assets下的station.json文件
            InputStream inputStream = getResources().getAssets().open("station.json");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String cityJson = new String(buffer, "utf-8");
            // 转化为站点实体类
            Gson gson = new Gson();
            StationBean stationBean = gson.fromJson(cityJson, StationBean.class);
            // 转换为String数组供Adapter使用
            int length = stationBean.getStation_info().size();
            stations = new String[length];
            for (int i = 0; i < length; i++)
                stations[i] = stationBean.getStation_info().get(i).getStation();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询车票
     */
    private void queryTicket()
    {
        String startStation = startStationTv.getText().toString();
        String endStation = endStationTv.getText().toString();
        if (TextUtils.isEmpty(startStation))
            ToastUtils.show(getContext(), R.string.start_station_in_hint);
        else if (TextUtils.isEmpty(endStation))
            ToastUtils.show(getContext(), R.string.end_station_in_hint);
        else if (endStation.equals(startStation))
            ToastUtils.show(getContext(), R.string.start_end_same_error_hint);
        else
        {
            String startDate = dateBtn.getText().toString();
            // 传递参数到下个页面
            Bundle bundle = new Bundle();
            bundle.putString("startStation", startStation);
            bundle.putString("endStation", endStation);
            bundle.putString("startDate", startDate);
            bundle.putString("trainType", trainType);
            turn(QueryTicketActivity.class, bundle);
            KeyBoardUtils.closeKeyboard(endStationTv, getContext());
        }
    }
}

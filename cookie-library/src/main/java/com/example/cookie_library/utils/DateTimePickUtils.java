package com.example.cookie_library.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cookie_library.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by maomao on 2019/4/8.
 * <p>
 * 日期选择器
 */

public class DateTimePickUtils implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener
{
    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity     调用的父activity
     * @param initDateTime 初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    public DateTimePickUtils(Activity activity, String initDateTime)
    {
        this.activity = activity;
        this.initDateTime = initDateTime;
    }

    /**
     * 初始化日期选择器
     *
     * @param datePicker
     */
    private void initDate(DatePicker datePicker)
    {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime)))
            calendar = this.getCalendarByInitData(initDateTime);
        else
            initDateTime = calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + "";

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
    }

    /**
     * 弹出日期选择框
     *
     * @param inputDate
     * @return
     */
    public AlertDialog datePicKDialog(final TextView inputDate)
    {
        LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater()
                .inflate(R.layout.view_date_time_pick, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.date_picker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.time_picker);
        timePicker.setVisibility(View.GONE);
        initDate(datePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);

        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime.substring(0, 10))
                .setView(dateTimeLayout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        inputDate.setText(dateTime.substring(0, 10));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        inputDate.setText(initDateTime.substring(0, 10));
                    }
                }).show();

        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
    {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        dateTime = sdf.format(calendar.getTime());
        ad.setTitle(dateTime);
    }

    /**
     * 实现将初始日期时间拆分成年月日时分秒，并赋值给calendar
     *
     * @param initDateTime 初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInitData(String initDateTime)
    {
        Calendar calendar = Calendar.getInstance();

        String yearStr = initDateTime.substring(0, 4);  // 年
        String monthStr = initDateTime.substring(5, 7); // 月
        String dayStr = initDateTime.substring(8, 10);  // 日
        String time = initDateTime.substring(11, 16);   // 时间
        String hourStr = splitString(time, ":", "index", "front");  // 时
        String minuteStr = splitString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour, currentMinute);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String splitString(String srcStr, String pattern,
                                     String indexOrLast, String frontOrBack)
    {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index"))
        {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        }
        else
        {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front"))
        {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        }
        else
        {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }
}

package com.example.trainticket_01.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maomao on 2019/4/7.
 * <p>
 * 获取当前日期时间
 */

public class DateTimeUtils
{
    private static DateTimeUtils instance;

    public static DateTimeUtils getInstance()
    {
        if (instance == null)
            instance = new DateTimeUtils();
        return instance;
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getCurrentTime()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentDate = new Date(System.currentTimeMillis());
        return timeFormat.format(currentDate);
    }

    /**
     * 获取当前日期
     * @return
     */
    public String getCurrentDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date currentDate = new Date(System.currentTimeMillis());
        return dateFormat.format(currentDate);
    }

    /**
     * 时间转为时间戳
     *
     * @param time
     * @return
     */
    public String dataToTimestamp(String time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String timestamp = null;
        try
        {
            Date date = formatter.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            timestamp = stf.substring(0, 10);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return timestamp;
    }
}

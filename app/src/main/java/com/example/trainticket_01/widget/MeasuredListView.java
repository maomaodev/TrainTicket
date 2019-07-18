package com.example.trainticket_01.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 自己适配高度的ListView，适用于嵌套在ScrollView内
 */

public class MeasuredListView extends ListView
{
    public MeasuredListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MeasuredListView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public MeasuredListView(Context context)
    {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

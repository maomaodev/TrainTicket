package com.example.cookie_library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 通用适配器
 */

public abstract class CommonAdapter<T> extends BaseAdapter
{
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(mContext, convertView,
                parent, mLayoutId, position);
        convert(commonViewHolder, getItem(position));
        return commonViewHolder.getConvertView();
    }

    public abstract void convert(CommonViewHolder holder, T t);
}

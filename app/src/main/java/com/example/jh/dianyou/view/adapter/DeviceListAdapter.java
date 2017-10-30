package com.example.jh.dianyou.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.data.entity.DeviceEn;
import com.example.jh.dianyou.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

public class DeviceListAdapter extends BaseAdapter {

    private List<DeviceEn> list = new ArrayList<>();
    private LayoutInflater mInflater;
    private final int ITEM_DEVICE = 0;
    private final int ITEM_DEVICE_ADD = 1;

    @Inject
    DeviceListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

    }

    public void setList(List<DeviceEn> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<DeviceEn> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return ITEM_DEVICE_ADD;
        } else {
            return ITEM_DEVICE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (getItemViewType(position) == ITEM_DEVICE) {
                convertView = mInflater.inflate(R.layout.item_device_list, null);
            } else {
                convertView = mInflater.inflate(R.layout.item_device_list_add, null);
            }

        }
        if (getItemViewType(position) == ITEM_DEVICE) {
            TextView title = (TextView) convertView.findViewById(R.id.tv_device);
            ImageView device = (ImageView) convertView.findViewById(R.id.iv_device);
            if (list.get(position).ischeck()) {
                title.setTextColor(Color.parseColor("#0695e1"));
                device.setImageResource(R.mipmap.ic_device_checked);
            } else {
                title.setTextColor(Color.BLACK);
                device.setImageResource(R.mipmap.ic_device);
            }

            title.setText(list.get(position).name().isEmpty() ? list.get(position).imei() : list.get(position).name());
        } else {

        }


        return convertView;
    }
}


package com.example.customlistview;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private List<item> mItemList;
    private Context mContext;

    public Adapter(List<item> mItemList, Context mContext) {
        this.mItemList = mItemList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_me,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.image = convertView.findViewById(R.id.hinhanh);
            viewHolder.nguoi = convertView.findViewById(R.id.detail);
            viewHolder.num = convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final item i = mItemList.get(position);
        viewHolder.nguoi.setText(i.getThongBao());
        viewHolder.num.setText(i.getGioDang());
        viewHolder.image.setImageResource(i.getAnh());
        return convertView;
    }
    class ViewHolder{
        ImageView image;
        TextView nguoi,num;
    }

}

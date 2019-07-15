package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Contact> contactArrayList;
    private Context context;
    private MyLongClick myLongClick;

    public Adapter(ArrayList<Contact> contactArrayList, Context context, MyLongClick myLongClick) {
        this.contactArrayList = contactArrayList;
        this.context = context;
        this.myLongClick = myLongClick;
    }

    public Adapter(ArrayList<Contact> contactArrayList, Context context) {
        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtN= convertView.findViewById(R.id.chu);
            viewHolder.txtNum=convertView.findViewById(R.id.so);
            viewHolder.linearLayout = convertView.findViewById(R.id.linear);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        final Contact a = contactArrayList.get(position);
        viewHolder.txtN.setText(a.getName().toString());
        viewHolder.txtNum.setText(a.getPhone().toString());
        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myLongClick.onLongClick(position);
                return false;
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLongClick.onClick(position);
            }
        });
        return convertView;
    }

    public void notifyDataSetChanged(ArrayList<Contact> tri) {
        this.contactArrayList = tri;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView txtN,txtNum;
        LinearLayout linearLayout;
    }
}

package com.example.trichatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatStatusAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> arrayList;

    public ChatStatusAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_list_chat,null);
        TextView danhxung = convertView.findViewById(R.id.danh);
        CircleImageView onl = convertView.findViewById(R.id.online);
        CircleImageView off = convertView.findViewById(R.id.offline);
        if(arrayList.get(position).getStatus()!= null && arrayList.get(position).getStatus().equals("offline")){
            off.setVisibility(View.VISIBLE);
            onl.setVisibility(View.GONE);
        }
        if(arrayList.get(position).getStatus()!= null && arrayList.get(position).getStatus().equals("online")){
            onl.setVisibility(View.VISIBLE);
            off.setVisibility(View.GONE);
        }

        danhxung.setText(arrayList.get(position).getTen());
        return convertView;
    }
}

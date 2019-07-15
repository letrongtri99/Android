package com.example.trichatapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    Context context;
    ArrayList<Chat> data;

    public ChatAdapter(Context context, ArrayList<Chat> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SharedPreferences preferences = context.getSharedPreferences("mydata",Context.MODE_PRIVATE);
        String currentUser = preferences.getString("nguoidung","");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(data.get(position).getSender().equals(currentUser)){
            convertView = inflater.inflate(R.layout.item_chat_right,null);
        }
        else {
            convertView = inflater.inflate(R.layout.item_chat_left, null);
        }
        TextView message = convertView.findViewById(R.id.itemchat);
        message.setText(data.get(position).getMessage());
        return convertView;
    }
}

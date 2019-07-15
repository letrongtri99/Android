package com.example.toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<String>  {
    private Context context;
    private ArrayList<String> arrayList ;
    private LayoutInflater layoutInflater;
    public Resources r;

    public CustomSpinnerAdapter(Context context, ArrayList<String> object) {
        super(context,R.layout.spinner_row,object);
        this.context = context;
        arrayList = object;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    public View getCustomView(int position, View convertView,  ViewGroup parent){
        View row = layoutInflater.inflate(R.layout.spinner_row,parent,false);
        TextView hang = row.findViewById(R.id.row);
        hang.setText(arrayList.get(position).toString());
        return row;
    }
}

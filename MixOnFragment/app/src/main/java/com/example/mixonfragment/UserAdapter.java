package com.example.mixonfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> arrayList;
    Context context;

    public UserAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {
        viewHolder.nguoi.setText(arrayList.get(i).getTenNguoi());
        viewHolder.so.setText(arrayList.get(i).getsDthoai());
        viewHolder.pic.setImageResource(arrayList.get(i).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nguoi, so;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nguoi = itemView.findViewById(R.id.detail);
            so = itemView.findViewById(R.id.time);
            pic = itemView.findViewById(R.id.hinhanh);
        }
    }
}

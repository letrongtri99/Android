package com.example.bookfood;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> arrayList;

    public ProductAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_food,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Product product = arrayList.get(i);
        Glide.with(context).load(product.getImage()).into(viewHolder.imgDoAn);
        viewHolder.textName.setText(product.getpName());
        viewHolder.textGia.setText(product.getBaseOfUnit());
        viewHolder.textDonvi.setText(product.getUnitPrice());
        viewHolder.textMieuTa.setText(product.getDesc());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgDoAn;
        TextView textName,textGia,textDonvi,textMieuTa;
        Button trI;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                imgDoAn = itemView.findViewById(R.id.imgAnh);
                textName = itemView.findViewById(R.id.txtName);
                textGia = itemView.findViewById(R.id.txtGia);
                textDonvi = itemView.findViewById(R.id.txtDonVi);
                textMieuTa = itemView.findViewById(R.id.txtMieuTa);
        }
    }
}

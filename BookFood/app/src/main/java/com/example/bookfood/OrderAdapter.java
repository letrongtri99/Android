package com.example.bookfood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    ArrayList<Order> arrayList;

    public OrderAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Order order = arrayList.get(i);
        Glide.with(context).load(order.getImage()).into(viewHolder.imgProduct);
        viewHolder.txtProduct.setText(order.getpName());
        viewHolder.txtQuanity.setText(order.getTotalitem());
        viewHolder.txtGia.setText(order.getTotalprice());
        viewHolder.txtSender.setText(order.getuName());
        String mStatus = order.getStatus();
        if(mStatus.equals("1")){
            viewHolder.txtStatus.setText("Đã đặt mua");
        }
        else{
            viewHolder.txtStatus.setText("Đã hủy");
        }
        viewHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pComment = viewHolder.edtComment.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Order").child(order.getpName()).child("comment").setValue(pComment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context,"Xác nhận thành công",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgProduct;
        TextView txtProduct,txtQuanity,txtGia,txtSender,txtStatus;
        EditText edtComment;
        Button btnAccept;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgSanPham);
            txtProduct = itemView.findViewById(R.id.nameofProduct);
            txtQuanity = itemView.findViewById(R.id.quantity);
            txtGia = itemView.findViewById(R.id.priceofProduct);
            txtSender = itemView.findViewById(R.id.nameofPerson);
            txtStatus = itemView.findViewById(R.id.statusofProduct);
            edtComment = itemView.findViewById(R.id.binhLuan);
            btnAccept = itemView.findViewById(R.id.accept);
        }
    }
}

package com.example.bookfoodkotlin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bookfoodkotlin.Activity.R
import com.example.bookfoodkotlin.Class.MyClickOrder
import com.example.bookfoodkotlin.Class.Order
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_order_item.view.*
import java.util.ArrayList

class OrderAdapter(internal var context: Context, internal var arrayList: ArrayList<Order>,internal var myClickOrder: MyClickOrder) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_order_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val order = arrayList[i]
        Glide.with(context).load(order.getImage()).into(viewHolder.itemView.imgSanPham)
        viewHolder.itemView.nameofProduct.setText(order.getpName())
        viewHolder.itemView.quantity.setText(order.getTotalitem())
        viewHolder.itemView.priceofProduct.setText(order.getTotalprice())
        viewHolder.itemView.nameofPerson.setText(order.getuName())
        val mStatus = order.getStatus()
        if (mStatus == "1") {
            viewHolder.itemView.statusofProduct.text = "Đã đặt mua"
        } else {
            viewHolder.itemView.statusofProduct.text = "Đã hủy"
        }
        viewHolder.itemView.accept.setOnClickListener {
            myClickOrder.ClickOrder(i)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init { }
    }
}
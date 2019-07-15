package com.example.bookfoodkotlin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bookfoodkotlin.Activity.R
import com.example.bookfoodkotlin.Class.MyClick
import com.example.bookfoodkotlin.Class.Product
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_list_food.view.*
import android.text.method.TextKeyListener.clear
import java.util.*
import kotlin.collections.ArrayList


class ProductAdapter(
    internal var context: Context,
    internal var arrayList: ArrayList<Product>,
    internal var myClick: MyClick
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_food, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val product = arrayList[i]
        Glide.with(context).load(product.getImage()).into(viewHolder.itemView.imgAnh)
        viewHolder.itemView.txtName.setText(product.getpName())
        viewHolder.itemView.txtGia.setText(product.getBaseOfUnit())
        viewHolder.itemView.txtDonVi.setText(product.getUnitPrice())
        viewHolder.itemView.txtMieuTa.setText(product.getDesc())
        viewHolder.itemView.adjust.setOnClickListener {
            myClick.onClick(i)

        }
        viewHolder.itemView.delete.setOnClickListener {
            myClick.onDeleteClick(i)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }



}
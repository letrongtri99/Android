package com.example.bookfoodkotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.bookfoodkotlin.Class.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_order_information.*

class OrderActivityInformation : AppCompatActivity() {
    val myData = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_information)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val intent = getIntent()
        val tenSP = intent.getStringExtra("ten")
        var nhanXet: String
        myData.child("Order").child(tenSP).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val order = p0.getValue(Order::class.java)!!
                tenofNgGui.setText(order.getuName())
                Glide.with(applicationContext).load(order.getImage()).into(picofOrder)
                tenofSpOrder.setText(order.getpName())
                txtofSoLuong.setText(order.getTotalitem())
                txtThanhTien.setText(order.getTotalprice())
                timeofOrder.setText(order.getTimeorder())
                if(order.getStatus().equals("1")){
                    statusofOrder.setText("Đã Mua")
                }
                else{
                    statusofOrder.setText("Đã Hủy")
                }
            }

        })
        btnAccept.setOnClickListener {
            nhanXet = comment.text.toString()
            myData.child("Order").child(tenSP).child("comment").setValue(nhanXet)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                android.R.id.home->{
                    onBackPressed()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

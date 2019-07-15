package com.example.bookfoodkotlin.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.bookfoodkotlin.Adapter.OrderAdapter
import com.example.bookfoodkotlin.Class.MyClickOrder
import com.example.bookfoodkotlin.Class.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_order.*
import java.util.ArrayList

class OrderActivity : AppCompatActivity() {
    internal var myData = FirebaseDatabase.getInstance().reference
    internal var arrayList = ArrayList<Order>()
    internal lateinit var orderAdapter: OrderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        orderAdapter = OrderAdapter(applicationContext, arrayList,object :MyClickOrder{
            override fun ClickOrder(position: Int) {
                val intent = Intent(this@OrderActivity,OrderActivityInformation::class.java)
                intent.putExtra("ten",arrayList[position].getpName())
                startActivity(intent)
            }
        })
        listorder.layoutManager = linearLayoutManager
        listorder.adapter = orderAdapter
        myData.child("Order").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                arrayList.clear()
                for (snapshot in dataSnapshot.children) {
                    val order = snapshot.getValue(Order::class.java)
                    if (order != null) {
                        arrayList.add(order)
                    }
                }
                orderAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
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

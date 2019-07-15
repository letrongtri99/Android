package com.example.bookfoodkotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bookfoodkotlin.Class.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_information.*

class ActivityInformation : AppCompatActivity() {
    lateinit var uri:String
    internal val myData = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val intent = getIntent()
        val id = intent.getStringExtra("vitri")
        myData.child("Product").child(id).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val product = dataSnapshot.getValue(Product::class.java)!!
                uri = product.getImage()
                Glide.with(applicationContext).load(uri).into(imgPicture)
                txtPrice.setText(product.getBaseOfUnit())
                txtdonvi.setText(product.getUnitPrice())
                txtMTa.setText(product.getDesc())
                txtTenRieng.setText(product.getpName())
            }

        })
        btnRepair.setOnClickListener {
            val product1 = Product(id,txtPrice.text.toString(),txtMTa.text.toString(),uri,txtTenRieng.text.toString(),txtdonvi.text.toString())
            myData.child("Product").child(id).setValue(product1).addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@ActivityInformation, "Chỉnh Sửa Thành công", Toast.LENGTH_LONG).show()
                }
            }
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

package com.example.bookfoodkotlin.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookfoodkotlin.Class.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var y:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        register.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivityForResult(intent, 20)
        }
        login.setOnClickListener(View.OnClickListener {
            val a = user.text.toString()
            val b = pass.text.toString()
            val databaseReference = FirebaseDatabase.getInstance().reference
            databaseReference.child("User").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var x: Long = dataSnapshot.childrenCount
                    for (snapshot in dataSnapshot.children) {
                        val user = snapshot.getValue<User>(User::class.java!!)!!
                        if (a == user!!.getuName() && b == user!!.getPassword()) {
                            val intent = Intent(this@MainActivity, ListFoodActivity::class.java)
                            startActivityForResult(intent, 8)
                            break
                        } else {
                            ++y
                            if(y == x) {
                                Toast.makeText(this@MainActivity, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        })
    }
}

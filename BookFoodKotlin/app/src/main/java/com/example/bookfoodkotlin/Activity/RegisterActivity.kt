package com.example.bookfoodkotlin.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bookfoodkotlin.Class.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    internal var databaseReference = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        dangki.setOnClickListener(View.OnClickListener {
            val a: String = ngdung.getText().toString()
            val b: String = mkhau.getText().toString()
            val c: String = userID.getText().toString()
            var user = User(b, c, a)
            var d: String = user.getuId()
            var x: String = DecodeString(d)
            if (!x.contains("/") && !x.contains(".") && !x.contains("#") && !x.contains("$") && !x.contains("[") && !x.contains(
                    "]")) {
                databaseReference.child("User").child(x).setValue(user).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Thành công", Toast.LENGTH_LONG).show()
                    }
                }
            }
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }

    public fun DecodeString(x: String): String {
        x.replace("/", "")
        x.replace(".", "")
        x.replace("#", "")
        x.replace("$", "")
        x.replace("[", "")
        x.replace("]", "")
        return x
    }
}

package com.example.bookfoodkotlin.Activity

import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bookfoodkotlin.Adapter.ProductAdapter
import com.example.bookfoodkotlin.Class.MyClick
import com.example.bookfoodkotlin.Class.Product
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_list_food.*
import kotlinx.android.synthetic.main.dialog_content.view.*
import java.util.*

class ListFoodActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    internal var myData = FirebaseDatabase.getInstance().reference
    internal var uri: Uri? = null
    internal var storage = FirebaseStorage.getInstance()
    internal lateinit var b: String
    internal lateinit var mUri: String
    internal var storageReference = storage.reference
    internal lateinit var circleImageView: CircleImageView
    internal lateinit var recyclerView: RecyclerView
    internal var arrayList = ArrayList<Product>()
    internal var arrayListSaved = ArrayList<Product>()
    internal lateinit var productAdapter: ProductAdapter
    private var storageTask: StorageTask<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        productAdapter = ProductAdapter(this@ListFoodActivity, arrayList, object : MyClick {
            override fun onDeleteClick(position: Int) {
                myData.child("Product").child(arrayList[position].getpId()).removeValue()
            }
            override fun onClick(position: Int) {
                val intent = Intent(this@ListFoodActivity,ActivityInformation::class.java)
                val o = arrayList[position].getpId()
                intent.putExtra("vitri",o)
                startActivity(intent)
            }
        })
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.divider)
        dividerItemDecoration.setDrawable(drawable!!)
        recycler.addItemDecoration(dividerItemDecoration)
        recycler.adapter = productAdapter
        myData.child("Product").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                arrayList.clear()
                for (snapshot in dataSnapshot.children) {
                    val product1 = snapshot.getValue(Product::class.java)!!
                    arrayList.add(product1)
                    arrayListSaved.add(product1)
                }
                productAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_product,menu)
        val searchView:SearchView = menu.findItem(R.id.search_bar).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.search_bar->{
//                var editsearch:SearchView = findViewById(R.id.search_bar);
//                editsearch.setOnQueryTextListener(this);
//            }
            R.id.add_bar ->{
                val builder = AlertDialog.Builder(this@ListFoodActivity)
                val view = LayoutInflater.from(this@ListFoodActivity).inflate(R.layout.dialog_content, null)
                builder.setView(view)
                val alertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
                view.escape.setOnClickListener { alertDialog.cancel() }
                view.add.setOnClickListener {
                    val a = view.describe.text.toString()
                    b = view.id1.text.toString()
                    val c = view.unit.text.toString()
                    val d = view.money.text.toString()
                    val e = view.tensanpham.text.toString()
                    val product = Product(b, d, a, mUri, e, c)
                    myData.child("Product").child(product.getpId()).setValue(product)
                    alertDialog.cancel()
                }
                circleImageView = view.findViewById(R.id.picture)
                circleImageView.setOnClickListener { OpenImage() }
            }
            R.id.history_bar -> {
                val u = Intent(this@ListFoodActivity, OrderActivity::class.java)
                startActivity(u)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun OpenImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 11)
    }

    private fun getFileExtension(uri: Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    private fun upLoadImage() {
        val pd = ProgressDialog(this@ListFoodActivity)
        pd.setMessage("Uploading...")
        pd.show()
        if (uri != null) {
            val fileReference =
                storageReference.child(System.currentTimeMillis().toString() + "." + getFileExtension(uri!!))
            var storageTask = fileReference.putFile(uri!!)
            storageTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    throw task.getException()!!
                }
                fileReference.downloadUrl
            }).addOnCompleteListener(OnCompleteListener<Uri> { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    mUri = downloadUri!!.toString()
                    Glide.with(applicationContext).load(mUri).into(circleImageView)
                    pd.dismiss()
                } else {
                    Toast.makeText(this@ListFoodActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }).addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this@ListFoodActivity, e.message, Toast.LENGTH_SHORT).show()
                pd.dismiss()
            })
        } else {
            Toast.makeText(this@ListFoodActivity, "No Image Selected", Toast.LENGTH_SHORT).show()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && data.data != null) {
            uri = data.data
            if (storageTask != null && storageTask!!.isInProgress()) {
                Toast.makeText(this@ListFoodActivity, "In progress...", Toast.LENGTH_SHORT).show()
            } else {
                upLoadImage()
            }

        }
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var text = newText
        if (text != null) {
            text = text.toLowerCase(Locale.getDefault())
        }
        arrayList.clear()
        if (text != null) {
            if (text.length == 0) {
                arrayList.addAll(arrayListSaved)
                productAdapter.notifyDataSetChanged()
            } else {
                for (wp in arrayListSaved) {
                    if (wp.getpName().toLowerCase(Locale.getDefault()).contains(text)) {
                        arrayList.add(wp)
                    }
                }
                productAdapter.notifyDataSetChanged()
            }
        }
        else{
            arrayList.addAll(arrayListSaved)
            productAdapter.notifyDataSetChanged()
        }
        return false
    }
}

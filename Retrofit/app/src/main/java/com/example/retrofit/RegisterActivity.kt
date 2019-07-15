package com.example.retrofit

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import android.provider.MediaStore
import android.util.Log
import com.example.retrofit.Retrofit2.APIUtils
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class RegisterActivity : AppCompatActivity() {
    lateinit var realPath:String
    lateinit var apiUtils: APIUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        userID.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent,1920)
        }
        dangki.setOnClickListener {
            var file:File = File(realPath)
            var file_path = file.absolutePath
            Log.d("bb",file_path)
//            var mangtenFile:List<String> = file_path.split("\\.")
//            Log.d("bb",mangtenFile.size.toString())
            file_path="/storage/emulated/0/DCIM/Camera/IMG_20190522_182509"+System.currentTimeMillis()+"."+"jpg"
            var requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file)
            var body:MultipartBody.Part = MultipartBody.Part.createFormData("upload_file",file_path,requestBody)
            var dataClient = apiUtils.getData()
            var callBack : retrofit2.Call<String> = dataClient.uploadPhoto(body)
            callBack.enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response!=null){
                        var message = response.body()

                    }
                }

            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1920 && resultCode == Activity.RESULT_OK && data!=null){
            var uri = data.data
            realPath = getRealPathFromURI(uri)!!
            var inputStream = contentResolver.openInputStream(uri)
            var bitmap = BitmapFactory.decodeStream(inputStream)
            userID.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getRealPathFromURI(contentUri: Uri): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = contentResolver.query(contentUri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            path = cursor.getString(column_index)
        }
        cursor.close()
        return path
    }
}

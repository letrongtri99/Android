package com.example.asynctask

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    var arrayList = ArrayList<String>()
    lateinit var adapterArray:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterArray = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,arrayList)
        listServer.adapter = adapterArray as ArrayAdapter<String>
        ReadContentUrl().execute("http://192.168.1.50/webservice/getData.php")
    }

    inner class ReadContentUrl : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String {
            var content: StringBuilder = StringBuilder()
            val url: URL = URL(params[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream = urlConnection.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            var line: String = ""
            try {
                do {
                    line = bufferedReader.readLine()
                    if(line!=null){
                        content.append(line)
                    }
                } while (line != null)
                bufferedReader.close()
            } catch (e: Exception) {
                Log.d("Tri", e.toString())
            }
            return content.toString()
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var jsonArray:JSONArray = JSONArray(result)
            for(khoahoc in 0..jsonArray.length()-1){
                var objectKH = jsonArray.getJSONObject(khoahoc)
                var ten = objectKH.getString("TenKH")
                var hphi = objectKH.getString("HocPhi")
                arrayList.add(ten+" - "+hphi)
            }
            adapterArray.notifyDataSetChanged()
        }
    }
}

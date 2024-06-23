package com.example.myapplication

import MyDataItem
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var txtId: TextView  // TextView-Referenz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtId = findViewById(R.id.txtId)

        getMyData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getMyData() {
      val retrofitBuilder = Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BASE_URL)
          .build()
          .create(Api::class.java)
        val retrofitData = retrofitBuilder.getData()
        // Asynchrone Anfrage
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()

                for( MyData in responseBody){
                    myStringBuilder.append(MyData.id)
                    myStringBuilder.append('\n')
                }
                runOnUiThread {
                    txtId.text = myStringBuilder.toString()
                }

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

                Log.e(TAG, "onFailure: " + t.message)
                t.printStackTrace()
            }
        })

    }
}
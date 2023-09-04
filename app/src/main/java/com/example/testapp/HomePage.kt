package com.example.testapp

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.api.RetrofitClient
import okhttp3.ResponseBody
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage : AppCompatActivity() {
    private lateinit var listView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_activity)
        getIndex()
    }

    private fun getIndex()
    {
        val apiService = RetrofitClient.instance

        apiService.getIndex().enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val body = response.body()?.string()
                    val jsonResponse = body?.let { JSONObject(it) }

                    val emailTextView = findViewById<TextView>(R.id.user_email)

                    if (jsonResponse != null) {
                        emailTextView.text = jsonResponse.get("email").toString()
                    }
                } else {
                    val code : Int = response.code()
                    println(code)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
            }
        })
    }
}

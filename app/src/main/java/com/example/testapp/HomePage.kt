package com.example.testapp

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.api.RetrofitClient
import com.example.testapp.entities.User
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
        val json: JSONObject? = intent.getStringExtra("user")?.let { JSONObject(it) }
        println(json?.get("email"))
    }
}

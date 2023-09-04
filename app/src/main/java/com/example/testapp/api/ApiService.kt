package com.example.testapp.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface ApiService {
    @GET("/test_call")
    fun getIndex(): Call<ResponseBody>
}
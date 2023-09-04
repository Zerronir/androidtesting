package com.example.testapp.api.datacoordinator

import org.json.JSONObject

public class dataCoordinator {
    fun makeRequest(requestUrl: String, payload: JSONObject): String
    {
        val headers: MutableMap<String, String> = HashMap<String, String>()
        headers["a-header-key"] = "a-sample-key"



        return "ok"
    }
}
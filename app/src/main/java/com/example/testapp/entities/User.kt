package com.example.testapp.entities

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
) {
    override fun toString(): String {
        return "{'id': " + id + ", 'username': '" + this.username + "', 'email': '" + this.email + "'}"
    }
}
package com.example.helderrocha.kanaproject.model
import com.google.gson.annotations.SerializedName

data class  User(
        @SerializedName("id") val id: Int?,
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url")  val avatar_url: String
)
package com.example.helderrocha.kanaproject.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
        @SerializedName("id") var id: Int?,
        @SerializedName("title") var title: String,
        @SerializedName("user") var user: User,
        @SerializedName("body") var body:  String

)
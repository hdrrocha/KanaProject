package com.example.helderrocha.kanaproject.model

import com.squareup.moshi.Json

data class  Owner(
        @Json(name = "id") var id: Int?,
        @Json(name = "login") var login: String,
        @Json(name = "avatar_url") var avatar_url: String
)
package com.example.helderrocha.kanaproject.model
import com.squareup.moshi.Json

data class  Items(
        @Json(name = "id") var id: Int?,
        @Json(name = "name") var name: String,
        @Json(name = "description") var description: String,
        @Json(name = "forks") var forks: String,
        @Json(name = "full_name") var full_name: String,
        @Json(name = "stargazers_count") var stargazers_count: String,
        @Json(name = "owner") var owner: Owner
)
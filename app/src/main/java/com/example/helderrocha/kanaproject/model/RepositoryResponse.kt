package com.example.helderrocha.kanaproject.model
import com.squareup.moshi.Json

data class RepositoryResponse(
        @Json(name = "items") val items: List<Items>?,
        @Json(name = "total_count") val totalCount: Int?
)
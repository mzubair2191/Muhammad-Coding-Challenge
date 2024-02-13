package com.example.myapplication.model.response


import com.google.gson.annotations.SerializedName

data class UpcomingGuides(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("total")
    val total: String?
)
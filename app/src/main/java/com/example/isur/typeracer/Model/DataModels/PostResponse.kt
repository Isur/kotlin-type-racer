package com.example.isur.typeracer.Model.DataModels

import com.squareup.moshi.Json

data class PostResponse(
    @Json(name = "success")
    val success:Boolean
)

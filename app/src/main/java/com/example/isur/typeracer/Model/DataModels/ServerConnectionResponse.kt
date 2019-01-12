package com.example.isur.typeracer.Model.DataModels

import com.squareup.moshi.Json

data class ServerConnectionResponse(
    @Json(name = "connection")
    val connection : Boolean
)
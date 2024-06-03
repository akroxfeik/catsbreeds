package com.swordhealth.catbreeds.data.model

import com.squareup.moshi.Json

data class Favourite(
    @Json(name = "id")
    val id: String,
    @Json(name = "user_id")
    val user_id: String,
    @Json(name = "image_id")
    val image_id: String,
    @Json(name = "sub_id")
    val sub_id: String,
    @Json(name = "created_at")
    val created_at: String
)

data class RequestFavourite(
    @Json(name = "image_id")
    val image_id: String,
    @Json(name = "sub_id")
    val sub_id: String
)

data class ResponseFavourite(
    @Json(name = "message")
    val message: String,
    @Json(name = "id")
    val id: String,
)

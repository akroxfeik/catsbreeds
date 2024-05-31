package com.swordhealth.catbreeds.data.model

import com.squareup.moshi.Json

data class Breed (
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "temperament")
    val temperament: String,
    @Json(name = "origin")
    val origin: String,
    @Json(name = "country_codes")
    val country_codes: String,
    @Json(name = "country_code")
    val country_code: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "life_span")
    val life_span: String,
    @Json(name = "wikipedia_url")
    val wikipedia_url: String,
    @Json(name = "weight")
    val weight: Weight,
    @Json(name = "reference_image_id")
    val reference_image_id: String)

data class Weight(
    @Json(name = "imperial")
    val imperial: String? = null,
    @Json(name = "metric")
    val metric: String? = null)

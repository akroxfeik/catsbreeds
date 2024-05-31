package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cats")
    suspend fun getBreeds(): Response<List<Breed>>
}
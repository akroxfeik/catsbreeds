package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breeds")
    suspend fun getBreeds(): Response<List<Breed>>

    @GET("breeds/{breedId}")
    suspend fun getBreed(@Path("breedId") breedId: String): Response<Breed>
}
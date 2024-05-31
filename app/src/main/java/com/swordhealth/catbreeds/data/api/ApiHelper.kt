package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import retrofit2.Response

interface ApiHelper {
    suspend fun getBreeds(): Response<List<Breed>>
}
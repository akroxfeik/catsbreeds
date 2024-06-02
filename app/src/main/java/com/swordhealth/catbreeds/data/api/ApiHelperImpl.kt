package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getBreeds(): Response<List<Breed>> = apiService.getBreeds()

    override suspend fun getBreed(breedId: String): Response<Breed> = apiService.getBreed(breedId)
}
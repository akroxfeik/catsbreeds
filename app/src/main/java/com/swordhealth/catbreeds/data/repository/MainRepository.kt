package com.swordhealth.catbreeds.data.repository

import com.swordhealth.catbreeds.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getBreeds() =  apiHelper.getBreeds()

    suspend fun getBreed(breedId: String) = apiHelper.getBreed(breedId)
}

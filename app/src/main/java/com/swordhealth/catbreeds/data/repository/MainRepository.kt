package com.swordhealth.catbreeds.data.repository

import com.swordhealth.catbreeds.data.api.ApiHelper
import com.swordhealth.catbreeds.data.model.RequestFavourite
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getBreeds() =  apiHelper.getBreeds()

    suspend fun getBreed(breedId: String) = apiHelper.getBreed(breedId)

    suspend fun getFavourites() = apiHelper.getFavourites()

    suspend fun getFavourite(id: String) = apiHelper.getFavourite(id)

    suspend fun addFavourite(addFavourite: RequestFavourite) = apiHelper.addFavourite(addFavourite)

    suspend fun removeFavourite(removeFavourite: RequestFavourite) = apiHelper.removeFavourite(removeFavourite)
}

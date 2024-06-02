package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.model.Favourite
import com.swordhealth.catbreeds.data.model.RequestFavourite
import com.swordhealth.catbreeds.data.model.ResponseFavourite
import retrofit2.Response

interface ApiHelper {
    suspend fun getBreeds(): Response<List<Breed>>

    suspend fun getBreed(breedId: String): Response<Breed>

    suspend fun getFavourites(): Response<List<Favourite>>

    suspend fun getFavourite(id: String): Response<Favourite>

    suspend fun addFavourite(addFavourite: RequestFavourite): Response<ResponseFavourite>

    suspend fun removeFavourite(removeFavourite: RequestFavourite): Response<ResponseFavourite>
}
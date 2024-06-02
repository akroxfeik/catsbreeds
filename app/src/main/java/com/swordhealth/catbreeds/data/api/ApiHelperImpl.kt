package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.model.Favourite
import com.swordhealth.catbreeds.data.model.RequestFavourite
import com.swordhealth.catbreeds.data.model.ResponseFavourite
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getBreeds(): Response<List<Breed>> = apiService.getBreeds()

    override suspend fun getBreed(breedId: String): Response<Breed> = apiService.getBreed(breedId)

    override suspend fun getFavourites(): Response<List<Favourite>> = apiService.getFavourites()

    override suspend fun getFavourite(id: String): Response<Favourite> = apiService.getFavourite(id)

    override suspend fun addFavourite(addFavourite: RequestFavourite): Response<ResponseFavourite> = apiService.addFavourite(addFavourite)

    override suspend fun removeFavourite(removeFavourite: RequestFavourite): Response<ResponseFavourite> = apiService.removeFavourite(removeFavourite)
}
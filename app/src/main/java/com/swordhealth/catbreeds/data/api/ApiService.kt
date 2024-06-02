package com.swordhealth.catbreeds.data.api

import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.model.Favourite
import com.swordhealth.catbreeds.data.model.RequestFavourite
import com.swordhealth.catbreeds.data.model.ResponseFavourite
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("breeds")
    suspend fun getBreeds(): Response<List<Breed>>

    @GET("breeds/{breedId}")
    suspend fun getBreed(@Path("breedId") breedId: String): Response<Breed>

    @GET("favourites")
    suspend fun getFavourites(): Response<List<Favourite>>

    @GET("favourites/{id}")
    suspend fun getFavourite(@Path("id") id: String): Response<Favourite>

    @POST("favourites")
    suspend fun addFavourite(@Body addFavourite: RequestFavourite): Response<ResponseFavourite>

    @DELETE("favourites")
    suspend fun removeFavourite(@Body removeFavourite: RequestFavourite): Response<ResponseFavourite>
}
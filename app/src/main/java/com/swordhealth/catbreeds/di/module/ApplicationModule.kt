package com.swordhealth.catbreeds.di.module

import com.swordhealth.catbreeds.BuildConfig
import com.swordhealth.catbreeds.data.api.ApiHelper
import com.swordhealth.catbreeds.data.api.ApiHelperImpl
import com.swordhealth.catbreeds.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideBaseUrl() = "https://api.thecatapi.com/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .apply {
                addHeader()
            }
            .build()
    } else OkHttpClient
        .Builder()
        .apply {
            addHeader()
        }
        .build()

    private fun OkHttpClient.Builder.addHeader() {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader(
                    "x-api-key",
                    "live_LT9ueIYw5PJjQ7tbrml7qs1wUbPz7Pec24MSFUDkC4dbaAtDJpYWzz75Q6zxZbSA"
                )
                return@Interceptor chain.proceed(builder.build())
            }
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}
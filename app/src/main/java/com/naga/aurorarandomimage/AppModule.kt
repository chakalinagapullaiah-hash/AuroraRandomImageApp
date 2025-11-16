package com.naga.aurorarandomimage

import com.naga.aurorarandomimage.data.remote.ApiService
import com.naga.aurorarandomimage.data.repository.ImageRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    private const val BASE_URL = "https://november7-730026606190.europe-west1.run.app/"
    // Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API service
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Repository (used by ViewModel)
    val repository: ImageRepository by lazy {
        ImageRepository(apiService)
    }
}
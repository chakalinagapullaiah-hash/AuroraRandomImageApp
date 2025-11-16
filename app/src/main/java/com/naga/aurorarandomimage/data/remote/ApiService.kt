package com.naga.aurorarandomimage.data.remote

import retrofit2.http.GET

data class ImageResponse(
    val url: String
)
interface ApiService {
    @GET("image")
    suspend fun getRandomImage(): ImageResponse
}

package com.naga.aurorarandomimage.data.repository

import com.naga.aurorarandomimage.data.remote.ApiService


class ImageRepository(private val api: ApiService) {
    suspend fun fetchRandomImage(): String {
        val response = api.getRandomImage()
        return response.url
    }
}

package com.example.wallpapers.repository

import android.util.Log
import com.example.wallpapers.api.ApiService.limit
import com.example.wallpapers.api.ImageApi
import com.example.wallpapers.dao.ImageDao
import com.example.wallpapers.dao.ImageEntity
import com.example.wallpapers.model.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImageRepositoryImpl(
    private val dao: ImageDao,
): ImageRepository {
    override val data: Flow<List<Image>> = dao.getImages().map {
        it.map(ImageEntity::toDto)
    }
    override suspend fun getImages(query: String) {
        val response = ImageApi.service.getImages(query)
        if (!response.isSuccessful) {
            throw error("Response code is ${response.code()}")
        }
        val unsplashResponse = response.body()
        val images = unsplashResponse?.results ?: throw RuntimeException("Body is empty")


        try {
            dao.clear()
            dao.insert(images.map(ImageEntity::fromDto))

        }catch (e: Exception) {

        }
    }

    override suspend fun getImageById(id: Long) {
        val response = ImageApi.service.getImageById(limit)
        if (!response.isSuccessful) {
            throw error("Response code is ${response.code()}")
        }

        val images = response.body() ?: throw RuntimeException("Body is empty")

        //dao.insert(images.map(ImageEntity::fromDto))
    }
}
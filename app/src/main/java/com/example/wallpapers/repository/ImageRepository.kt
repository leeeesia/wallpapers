package com.example.wallpapers.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.wallpapers.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    val data:Flow<List<Image>>
    suspend fun getImages(query: String)

    suspend fun getImageById(
        id: Long,
    )
}
package com.example.wallpapers.api

import com.example.wallpapers.api.ApiService.ACCESS_KEY
import com.example.wallpapers.model.Image
import com.example.wallpapers.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ImageApiService {
    @GET("search/photos")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("client_id") clientId: String = ACCESS_KEY,
        @Query("per_page") perPage: Int = 10
    ): Response<ImageResponse>


    @GET("api/")
    suspend fun getImageById(
        @Query("limit") limit: Int
    ): Response<Image>
}

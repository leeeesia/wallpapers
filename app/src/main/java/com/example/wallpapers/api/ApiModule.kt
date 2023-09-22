package com.example.wallpapers.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiService {

    val limit = 10
    val query = "flower"
    const val ACCESS_KEY = "_dc4rUVwKwkqlsmvN5dm8gJem1Cj2D3HMjpk_2YqBDE"

}

private const val BASE_URL = "https://api.unsplash.com/"

private val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY

}
private val okhttp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

object ImageApi {
    val service: ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }
}
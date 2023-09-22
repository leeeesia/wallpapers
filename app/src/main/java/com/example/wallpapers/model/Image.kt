package com.example.wallpapers.model

data class Image(
    val id: String,
    val urls: Urls?
)

data class ImageResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<Image>
)


data class Urls(
    val regular: String
)

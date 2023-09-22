package com.example.wallpapers.model

data class FeedModel(
    val images: List<Image> = emptyList(),
    val empty: Boolean = false,
)
data class FeedModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
    val response: FeedResponse = FeedResponse()
)
data class FeedResponse(
    val code : Int = 0,
    val message: String? = null
)

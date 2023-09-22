package com.example.wallpapers.dao

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpapers.model.Image
import com.example.wallpapers.model.Urls

@Entity
data class ImageEntity(
    @PrimaryKey
    val id: String,
    @Embedded
    val urls: Urls?
){
    fun toDto() = Image( id, urls)

    companion object {
        fun fromDto(dto: Image) =
            ImageEntity(
                dto.id,
                dto.urls
            )

    }
}

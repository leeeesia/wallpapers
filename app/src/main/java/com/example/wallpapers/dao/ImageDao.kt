package com.example.wallpapers.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM ImageEntity ORDER BY id DESC")
    fun getImages(): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<ImageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: ImageEntity)

    @Query("DELETE FROM ImageEntity")
    suspend fun clear()

}
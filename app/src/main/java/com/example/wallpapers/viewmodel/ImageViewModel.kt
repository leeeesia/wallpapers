package com.example.wallpapers.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.wallpapers.db.AppDb
import com.example.wallpapers.model.FeedModel
import com.example.wallpapers.model.FeedModelState
import com.example.wallpapers.model.Image
import com.example.wallpapers.repository.ImageRepository
import com.example.wallpapers.repository.ImageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


private val empty = Image(
    id = "",
    urls = null
)

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ImageRepository = ImageRepositoryImpl(
        AppDb.getInstance(application).imageDao()
    )
    var query = ""
    private val _state = MutableLiveData(FeedModelState())
    val state: LiveData<FeedModelState>
        get() = _state
    val date: LiveData<FeedModel> = repository.data.map {
        FeedModel(images = it, empty = it.isEmpty())
    }.asLiveData(Dispatchers.Default)

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            _state.postValue(FeedModelState(loading = true))
            try {
                repository.getImages(query)
                _state.postValue(FeedModelState())
            } catch (e: Exception) {
                _state.value = FeedModelState(error = true)
                e.printStackTrace()
            }
        }

    }

    fun updateQuery(newQuery: String) {
        query = newQuery
        clear()
        loadImages()
    }
    fun clear() {
        _state.value = null
    }
}
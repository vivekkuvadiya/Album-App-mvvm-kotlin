package com.example.demoalbumapp.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoalbumapp.remote.model.Albums
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _albums = MutableLiveData<Resource<List<List<Photo>>>>()
    val albums: LiveData<Resource<List<List<Photo>>>> = _albums


    fun getAlbum() {
        viewModelScope.launch {
            _albums.postValue(Resource.Loading())
            when (val albumResponse = repository.getAlbums()) {
                is Resource.Success -> {
                    albumResponse.data?.let {
                        _albums.postValue(Resource.Success(sortByAlbum(it)))
                    } ?: run {
                        _albums.postValue(Resource.Error("No Album Found"))
                    }
                }
                is Resource.Error -> {
                    _albums.postValue(
                        Resource.Error(
                            albumResponse.message ?: "unknown error occurred"
                        )
                    )
                }
                else -> Unit
            }
        }
    }


    private fun sortByAlbum(albums: Albums) =
        albums.groupBy { it.albumId }.map { (_, photosInAlbum) -> photosInAlbum }

}
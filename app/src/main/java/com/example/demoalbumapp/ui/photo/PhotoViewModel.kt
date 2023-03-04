package com.example.demoalbumapp.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

    private val _photo = MutableLiveData<Resource<Photo>>()
    val photo:LiveData<Resource<Photo>> = _photo

    fun getPhoto(id:String){
        viewModelScope.launch {
            _photo.postValue(Resource.Loading())
            when(val photoResponse = repository.getPhoto(id)){
                is Resource.Success -> {
                    photoResponse.data?.let {
                        _photo.postValue(Resource.Success(it))
                    }?: run {
                        _photo.postValue(Resource.Error("No Photo Found"))
                    }
                }
                is Resource.Error -> {
                    _photo.postValue(Resource.Error(photoResponse.message?:"unknown error occurred"))
                }
                else -> Unit
            }
        }
    }

}
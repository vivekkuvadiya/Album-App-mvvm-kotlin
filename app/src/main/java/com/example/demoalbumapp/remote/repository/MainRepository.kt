package com.example.demoalbumapp.remote.repository

import com.example.demoalbumapp.remote.model.Albums
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.utils.Resource

interface MainRepository  {

    suspend fun getAlbums():Resource<Albums>

    suspend fun getPhoto(id:String):Resource<Photo>

}
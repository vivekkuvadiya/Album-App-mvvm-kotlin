package com.example.demoalbumapp.remote

import com.example.demoalbumapp.remote.model.Albums
import com.example.demoalbumapp.remote.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("photos")
    suspend fun getAlbums():Response<Albums>

    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id:String
    ):Response<Photo>

}
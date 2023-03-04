package com.example.demoalbumapp.repository

import com.example.demoalbumapp.remote.AlbumApi
import com.example.demoalbumapp.remote.model.Albums
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.utils.Resource
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val albumApi: AlbumApi):MainRepository {

    override suspend fun getAlbums(): Resource<Albums> {
        return try {
            val response = albumApi.getAlbums()
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.Success(it)
                }?:Resource.Error(response.message())
            }else{
                Resource.Error(response.message())
            }
        }
        catch (e:IOException){
            Resource.Error("No Internet connection available")
        }
        catch (e:Exception){
            Resource.Error(e.message?:"unknown error occurred")
        }
    }

    override suspend fun getPhoto(id:String): Resource<Photo> {
        return try {
            val response = albumApi.getPhoto(id)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.Success(it)
                }?:Resource.Error(response.message())
            }else{
                Resource.Error(response.message())
            }
        } catch (e:IOException){
            Resource.Error("No Internet connection available")
        }catch (e:Exception){
            Resource.Error(e.message?:"unknown error occurred")
        }
    }
}
package com.example.demoalbumapp.di

import com.example.demoalbumapp.remote.AlbumApi
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.repository.MainRepositoryImpl
import com.example.demoalbumapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAlbumApi() = Retrofit.Builder()
        .baseUrl(Constants.ALBUM_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AlbumApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(albumApi: AlbumApi):MainRepository = MainRepositoryImpl(albumApi)

}
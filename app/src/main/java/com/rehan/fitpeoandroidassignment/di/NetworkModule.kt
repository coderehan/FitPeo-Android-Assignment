package com.rehan.fitpeoandroidassignment.di

import com.rehan.fitpeoandroidassignment.api.PhotosAPI
import com.rehan.fitpeoandroidassignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesPhotosAPI(retrofit: Retrofit): PhotosAPI {
        return retrofit.create(PhotosAPI::class.java)
    }
}
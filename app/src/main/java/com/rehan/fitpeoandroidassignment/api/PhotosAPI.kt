package com.rehan.fitpeoandroidassignment.api

import com.rehan.fitpeoandroidassignment.models.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotosAPI {

    @GET("/photos")
    suspend fun getPhotos(): Response<List<PhotosResponse>>
}
package com.rehan.fitpeoandroidassignment.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rehan.fitpeoandroidassignment.api.PhotosAPI
import com.rehan.fitpeoandroidassignment.models.PhotosResponse
import com.rehan.fitpeoandroidassignment.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val photosAPI: PhotosAPI) {

    private val _photosResponseLiveData = MutableLiveData<NetworkResult<List<PhotosResponse>>>()
    val photosResponseLiveData: LiveData<NetworkResult<List<PhotosResponse>>>
        get() = _photosResponseLiveData

    suspend fun getPhotos() {
        _photosResponseLiveData.postValue(NetworkResult.Loading())
        val response = photosAPI.getPhotos()

        if (response.isSuccessful && response.body() != null) {
            _photosResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObject = JSONObject(response.errorBody()!!.charStream().readText())
            _photosResponseLiveData.postValue(NetworkResult.Error(errorObject.getString("message")))
        } else {
            _photosResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}
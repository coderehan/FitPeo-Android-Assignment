package com.rehan.fitpeoandroidassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.fitpeoandroidassignment.repositories.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    val photosLiveData get() = photosRepository.photosResponseLiveData

    fun getPhotos() {
        viewModelScope.launch {
            photosRepository.getPhotos()
        }
    }
}
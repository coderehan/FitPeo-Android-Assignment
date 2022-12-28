package com.rehan.fitpeoandroidassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rehan.fitpeoandroidassignment.R
import com.rehan.fitpeoandroidassignment.adapters.PhotosAdapter
import com.rehan.fitpeoandroidassignment.databinding.ActivityMainBinding
import com.rehan.fitpeoandroidassignment.models.PhotosResponse
import com.rehan.fitpeoandroidassignment.utils.Constants
import com.rehan.fitpeoandroidassignment.utils.NetworkResult
import com.rehan.fitpeoandroidassignment.viewmodels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var photosResponse: PhotosResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        photosViewModel = ViewModelProvider(this)[PhotosViewModel::class.java]
        setPhotosRecyclerView()
        photosViewModel.getPhotos()
        observeLiveData()
        onPhotoItemsClick()
    }

    private fun setPhotosRecyclerView() {
        photosAdapter = PhotosAdapter()
        binding.rvPhotos.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = photosAdapter
        }
    }

    private fun observeLiveData() {
        photosViewModel.photosLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            when(it){
                is NetworkResult.Success ->{
                    photosAdapter.setPhotosList(it.data!!)
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        })
    }

    // Passing data from this activity to another activity on click of recyclerview items
    private fun onPhotoItemsClick() {
        photosAdapter.onItemClick = {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra(Constants.ID, it.id)
            intent.putExtra(Constants.ALBUM_ID, it.albumId)
            intent.putExtra(Constants.TITLE, it.title)
            intent.putExtra(Constants.URL, it.url)
            intent.putExtra(Constants.THUMBNAIL_URL, it.thumbnailUrl)
            startActivity(intent)
        }
    }

}
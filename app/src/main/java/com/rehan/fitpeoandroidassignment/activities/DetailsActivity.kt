package com.rehan.fitpeoandroidassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.rehan.fitpeoandroidassignment.R
import com.rehan.fitpeoandroidassignment.databinding.ActivityDetailsBinding
import com.rehan.fitpeoandroidassignment.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var id by Delegates.notNull<Int>()
    private var albumId by Delegates.notNull<Int>()
    private lateinit var title: String
    private lateinit var url: String
    private lateinit var thumbnailUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        supportActionBar!!.hide()
        binding.ivBack.setOnClickListener {
            finish()
        }
        getPhotosInformationFromIntent()
        setInformationInViews()
    }

    private fun getPhotosInformationFromIntent() {
        // Getting data from source activity and setting data in this destination activity
        val intent = intent
        id = intent.getIntExtra(Constants.ID, 1)
        albumId = intent.getIntExtra(Constants.ALBUM_ID, 1)
        title = intent.getStringExtra(Constants.TITLE).toString()
        url = intent.getStringExtra(Constants.URL).toString()
        thumbnailUrl = intent.getStringExtra(Constants.THUMBNAIL_URL).toString()
    }

    private fun setInformationInViews() {
        Picasso.get().load(thumbnailUrl).into(binding.ivThumbnailPhoto)
        Picasso.get().load(url).into(binding.ivMainPhoto)
        binding.tvActionBar.text = "Id : $id"
        binding.tvId.text = id.toString()
        binding.tvAlbumId.text = albumId.toString()
        binding.tvTitle.text = title
    }

}
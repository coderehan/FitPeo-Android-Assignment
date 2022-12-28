package com.rehan.fitpeoandroidassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rehan.fitpeoandroidassignment.databinding.AdapterPhotosItemsBinding
import com.rehan.fitpeoandroidassignment.models.PhotosResponse

class PhotosAdapter(): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private var photosList = ArrayList<PhotosResponse>()
    var onItemClick: ((PhotosResponse) -> Unit)? = null

    fun setPhotosList(photosList: List<PhotosResponse>){
        this.photosList = photosList as ArrayList<PhotosResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPhotosItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.photosResponse = photosList[position]

        // When user clicks on item views in recyclerview
        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(photosList[position])
        }

    }

    override fun getItemCount(): Int {
        return photosList.size
    }


    inner class ViewHolder(val binding: AdapterPhotosItemsBinding): RecyclerView.ViewHolder(binding.root) {

    }

}
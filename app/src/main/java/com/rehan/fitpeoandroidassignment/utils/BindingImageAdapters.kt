package com.rehan.fitpeoandroidassignment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {

    url?.let {
        Picasso.get().load(url).into(view)
    }

}
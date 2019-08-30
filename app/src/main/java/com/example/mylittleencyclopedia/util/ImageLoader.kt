package com.example.mylittleencyclopedia.util

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import jp.wasabeef.picasso.transformations.CropCircleTransformation as CropCircleTransformation

interface ImageLoaderCallback : Callback {
    override fun onSuccess()
    override fun onError(e: Exception)
}

fun picassoLoader(uri: String, imageView: ImageView) {
    Picasso.get()
        .load(uri)
        .into(imageView)
}
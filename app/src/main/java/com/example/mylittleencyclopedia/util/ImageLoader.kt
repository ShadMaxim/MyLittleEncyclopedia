package com.example.mylittleencyclopedia.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun picassoLoader(uri: String, imageView: ImageView) {
    Picasso.get()
        .load(uri)
        .into(imageView)
}
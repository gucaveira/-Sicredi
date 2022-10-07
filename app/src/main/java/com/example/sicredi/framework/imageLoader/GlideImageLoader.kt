package com.example.sicredi.framework.imageLoader

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader {
    override fun load(imageView: ImageView, imageUrl: String, placerHolder: Int, fallback: Int) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .placeholder(placerHolder)
            .fallback(fallback)
            .into(imageView)
    }
}
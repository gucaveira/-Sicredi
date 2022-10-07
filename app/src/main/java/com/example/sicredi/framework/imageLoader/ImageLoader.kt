package com.example.sicredi.framework.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.sicredi.R

interface ImageLoader {
    fun load(
        imageView: ImageView, imageUrl: String,
        @DrawableRes placerHolder: Int = R.drawable.ic_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_loading_error
    )
}
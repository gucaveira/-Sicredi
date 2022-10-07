package com.example.sicredi.presentation.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DetailViewArg(
    val eventId: String,
    val name: String,
    val description: String,
    val imageUrl: String,
) : Parcelable
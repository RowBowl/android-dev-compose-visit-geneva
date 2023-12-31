package com.example.visitgeneva.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    @StringRes val titleResId: Int,
    @StringRes val subTitleResId: Int,
    @StringRes val detailsResId: Int,
    @DrawableRes val imageResId: Int,
    val recommendations: List<Recommendation>
)

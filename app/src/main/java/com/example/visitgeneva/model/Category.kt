package com.example.visitgeneva.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
    val recommendationsCount: Int
)

package com.example.visitgeneva.data

import androidx.compose.material.icons.Icons
import com.example.visitgeneva.R
import com.example.visitgeneva.model.Category

object LocalCategoriesProvider {
    val defaultCategory = getCategories()[0]

    public fun getCategories(): List<Category> {
        return listOf(
            Category(
                id = 0,
                titleResId = R.string.category_title_1,
                subTitleResId = R.string.category_subtitle_1,
                iconResId = R.drawable.category_icon_1,
                recommendationsCount = 0
            )
        )
    }
}
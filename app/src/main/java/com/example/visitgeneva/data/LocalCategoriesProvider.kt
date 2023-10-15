package com.example.visitgeneva.data

import com.example.visitgeneva.R
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation

object LocalCategoriesProvider {
    val defaultCategory = getCategories()[0]

    public fun getCategories(): List<Category> {
        return listOf(
            Category(
                id = 0,
                titleResId = R.string.category_title_1,
                subTitleResId = R.string.category_subtitle_1,
                detailsResId = R.string.category_detail_1,
                imageResId = R.drawable.category_icon_1,
                recommendations = listOf(
                    Recommendation(
                        id = 0,
                        titleResId = R.string.category_recommendation_title_1,
                        descriptionResId = R.string.category_recommendation_description_1,
                        imageResId = R.drawable.category_recommendation_img_1
                    )
                )
            )
        )
    }

    public fun getFirstRecommendations(): List<Recommendation> {
        return defaultCategory.recommendations
    }
}
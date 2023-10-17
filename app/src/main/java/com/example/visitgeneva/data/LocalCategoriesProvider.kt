package com.example.visitgeneva.data

import com.example.visitgeneva.R
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation

object LocalCategoriesProvider {
    //val defaultRecommendation = getFirstCategoryRecommendations()[0]
    val defaultCategory = getCategories()[0]

    public fun getCategories(): List<Category> {
        return listOf(
            Category(
                id = 1,
                titleResId = R.string.category_title_1,
                subTitleResId = R.string.category_subtitle_1,
                detailsResId = R.string.category_detail_1,
                imageResId = R.drawable.category_icon_1,
                recommendations = listOf(
                    Recommendation(
                        id = 1,
                        titleResId = R.string.category_recommendation_title_1,
                        descriptionResId = R.string.category_recommendation_description_1,
                        imageResId = R.drawable.category_recommendation_img_1
                    ),
                    Recommendation(
                        id = 2,
                        titleResId = R.string.category_recommendation_title_2,
                        descriptionResId = R.string.category_recommendation_description_2,
                        imageResId = R.drawable.category_recommendation_img_2
                    ),
                    Recommendation(
                        id = 3,
                        titleResId = R.string.category_recommendation_title_3,
                        descriptionResId = R.string.category_recommendation_description_3,
                        imageResId = R.drawable.category_recommendation_img_3
                    ),

                )
            ),
            Category(
                id = 2,
                titleResId = R.string.category_title_2,
                subTitleResId = R.string.category_subtitle_2,
                detailsResId = R.string.category_detail_2,
                imageResId = R.drawable.category_icon_2,
                recommendations = listOf(
                    Recommendation(
                        id = 1,
                        titleResId = R.string.category_recommendation_title_1,
                        descriptionResId = R.string.category_recommendation_description_1,
                        imageResId = R.drawable.category_recommendation_img_1
                    )
                )
            )
        )
    }
}
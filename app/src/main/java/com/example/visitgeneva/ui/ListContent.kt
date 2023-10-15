package com.example.visitgeneva.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.R
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.ItemCardType


@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(recommendations) {recommendation ->
            RecommendationItem(
                recommendation = recommendation,
                showFullDetail = false,
                onRecClick = {},
                modifier = Modifier
            )
        }
    }
}

enum class ListType {
    Horizontal,
    Vertical
}

@Composable
fun CategoryList(
    categories: List<Category>,
    listType: ListType,
    onCategoryClick: (Category) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    when (listType) {
        ListType.Horizontal -> {
            LazyRow(
                contentPadding = contentPadding,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(6) {item ->
                    CategoryItem(
                        itemType = ItemCardType.ListItem,
                        category = LocalCategoriesProvider.defaultCategory,
                        onCategoryClick = onCategoryClick,
                        onBackPressed = {},
                        modifier = Modifier.size(400.dp)
                    )
                }
            }
        }
        ListType.Vertical -> {
            LazyColumn(
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(6) {item ->
                    CategoryItem(
                        itemType = ItemCardType.ListItem,
                        category = LocalCategoriesProvider.defaultCategory,
                        onCategoryClick = onCategoryClick,
                        onBackPressed = {},
                        modifier = Modifier.size(400.dp, 200.dp)
                    )
                }
            }
        }
    }

}


@Composable
fun CategoryDetailsAndItems(
    currentCategory: Category,
    isFullScreen: Boolean,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed
    }
    Card (
        modifier = modifier
    ) {
        Box(modifier = Modifier) {

            Text(text = stringResource(id = R.string.category_detail_1))
            LazyColumn(
            ) {
                items(currentCategory.recommendations) {

                }
            }
            CategoryImage(category = currentCategory)
        }
    }
}


@Composable
@Preview
fun CategoryListHorizontalPreview() {
    VisitGenevaTheme {
        CategoryList(
            categories = LocalCategoriesProvider.getCategories(),
            listType = ListType.Horizontal,
            onCategoryClick = {},
            modifier = Modifier
        )
    }
}
@Composable
@Preview
fun CategoryListVerticalPreview() {
    VisitGenevaTheme {
        CategoryList(
            categories = LocalCategoriesProvider.getCategories(),
            listType = ListType.Vertical,
            onCategoryClick = {},
            modifier = Modifier
        )
    }
}

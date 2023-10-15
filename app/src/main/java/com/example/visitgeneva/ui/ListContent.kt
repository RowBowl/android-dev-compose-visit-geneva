package com.example.visitgeneva.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.R
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.ItemCardType

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
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(categories) {category ->
                    CategoryItem(
                        itemType = ItemCardType.ListItem,
                        category = category,
                        onCategoryClick = onCategoryClick,
                        onBackPressed = {},
                        modifier = Modifier.size(300.dp)
                    )
                }
            }
        }
        ListType.Vertical -> {
            LazyColumn(
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(categories) {category ->
                    CategoryItem(
                        itemType = ItemCardType.ListItem,
                        category = LocalCategoriesProvider.defaultCategory,
                        onCategoryClick = onCategoryClick,
                        onBackPressed = {},
                        modifier = Modifier.height(200.dp)
                    )
                }
            }
        }
    }
}

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


@Composable
fun CategoryDetailsAndRecommendations(
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
        Box(modifier = Modifier.fillMaxSize()) {
            //CategoryImage(category = currentCategory)
            Column (
                modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))
            ){
                Text(
                    text = stringResource(id = R.string.category_detail_1),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier.height(50.dp))
                Text(
                    text = "Recommended Places: ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                RecommendationList(
                    recommendations = currentCategory.recommendations
                )
            }
        }
    }
}

@Composable
@Preview
fun CategoryDetailsAndRecommendationsPreview() {
    VisitGenevaTheme {
        CategoryDetailsAndRecommendations(
            currentCategory = LocalCategoriesProvider.defaultCategory,
            isFullScreen = true,
            onBackPressed = { }
        )
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

package com.example.visitgeneva.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onRecClick: (Recommendation) -> Unit,
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
                        onItemClick = onCategoryClick,
                        onRecClick = onRecClick,
                        modifier = Modifier.width(300.dp).height(400.dp)
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
                        category = category,
                        onItemClick = onCategoryClick,
                        onRecClick = onRecClick,
                        modifier = Modifier.width(400.dp).height(200.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    modifier: Modifier = Modifier,
    onRecClick: (Recommendation) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        items(recommendations) {recommendation ->
            RecommendationItem(
                recommendation = recommendation,
                showFullDetail = false,
                onRecClick = onRecClick,
                modifier = Modifier
            )
        }
    }
}

/*

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
*/
/*
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
}*/

@Composable
@Preview
fun CategoryListHorizontalPreview() {
    VisitGenevaTheme {
        CategoryList(
            categories = LocalCategoriesProvider.getCategories(),
            listType = ListType.Horizontal,
            onCategoryClick = {},
            onRecClick = {},
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
            onRecClick = {},
            modifier = Modifier
        )
    }
}

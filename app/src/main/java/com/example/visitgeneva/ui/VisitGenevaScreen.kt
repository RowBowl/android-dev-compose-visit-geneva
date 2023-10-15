package com.example.visitgeneva.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.R
import com.example.visitgeneva.model.Recommendation
import com.example.visitgeneva.utils.ContentType
import com.example.visitgeneva.utils.ItemCardType
import com.example.visitgeneva.utils.UiUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitGenevaApp(windowSize: WindowSizeClass) {
    val contentType: ContentType
    val viewModel: VisitGenevaViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            contentType = ContentType.GridAndDetail
        }
        WindowWidthSizeClass.Medium -> {
            contentType = ContentType.GridAndDetail
        }
        WindowWidthSizeClass.Compact -> {
            contentType = ContentType.GridOnly
        }
        else -> {
            contentType = ContentType.GridOnly
        }
    }

    Scaffold (
        topBar = {

        }
    ){ innerPadding ->
        if(uiState.isShowingGridPage) {
            CategoryGrid(
                categories = uiState.categoryGrid,
                contentPadding = innerPadding,
                onCategoryClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.navigateToDetailPage()
                }
            )
        } else {
            CategoryDetailsAndItems(
                currentCategory = uiState.currentCategory,
                isFullScreen = true,
                onBackPressed = {viewModel.navigateToListPage()},
                contentPadding = innerPadding
            )
        }
        /*if (contentType == ContentType.GridAndDetail) {

        } else {
            if(uiState.isShowingGridPage) {
                CategoryList(
                    categories = uiState.categoryList,
                    contentPadding = innerPadding,
                    onCategoryClick = {
                        viewModel.updateCurrentCategory(it)
                        viewModel.navigateToDetailPage()
                    }
                )
            }
        }*/

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
fun CategoryItem(
    itemType: ItemCardType,
    category: Category,
    onCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mod: Modifier
    val brush: Brush

    when (itemType) {
        ItemCardType.ListItem -> {
            mod = Modifier.height(200.dp)
            brush = UiUtils.brush1
        }
        ItemCardType.DetailItem -> {
            mod = Modifier.fillMaxHeight()
            brush = UiUtils.brush2
        }
    }
    Card(
        modifier = modifier.shadow(elevation = 4.dp)
    ) {
        Box(
            modifier = mod.drawCardBackground(brush)
            //TODO move height/width values to resources
        ) {
            CategoryImage(
                category = category,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .alpha(0.85f)
                )
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                when (itemType) {
                    ItemCardType.ListItem -> {
                        Text(
                            text = stringResource(id = category.subTitleResId),
                            color = MaterialTheme.colorScheme.onPrimary.copy(
                                alpha = 0.75f
                            ),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = stringResource(id = category.titleResId),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                    ItemCardType.DetailItem -> {
                        Text(
                            text = stringResource(id = category.titleResId),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = stringResource(id = category.detailsResId),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        RecommendationList(
                            recommendations = category.recommendations,
                            modifier = Modifier
                        )
                    }
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
@Preview
fun RecommendationItemPreview() {
    RecommendationItem(
        recommendation = LocalCategoriesProvider.getFirstRecommendations().firstOrNull()!!,
        onRecClick = {},
        showFullDetail = false
    )
}
@Composable
fun RecommendationItem(
    showFullDetail: Boolean,
    recommendation: Recommendation,
    onRecClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    val mod: Modifier
    val spacedBy: Dp

    if (showFullDetail) {
        mod = modifier.fillMaxHeight()
        spacedBy = 40.dp
    }  else {
        mod = modifier
            .height(150.dp)
            .verticalScroll(scrollState)
        spacedBy = 10.dp
    }

    Surface (
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, UiUtils.brush2),
        shape = MaterialTheme.shapes.small,
        modifier = mod
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(spacedBy, Alignment.Top)
        ){
            Text(
                text = stringResource(id = recommendation.titleResId),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = recommendation.descriptionResId),
                style = MaterialTheme.typography.bodyLarge
            )
            if(showFullDetail) {
                Image(
                    painter = painterResource(id = recommendation.imageResId),
                    contentDescription = "Recommendation Image",
                    modifier = Modifier.clip(MaterialTheme.shapes.large)
                )
            }
        }
    }
}

private fun Modifier.drawCardBackground(brush: Brush): Modifier {
    return then(Modifier.drawWithCache {
        onDrawBehind {
            drawRoundRect(
                brush,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
    })
}

@Composable
fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = category.imageResId),
        contentDescription = "Category Grid Item",
        modifier = modifier
    )
}
/*
@Composable
@Preview
fun CategoryDetailPreview() {
    VisitGenevaTheme {
        CategoryItem(itemType = DetailItem, category = , onCategoryClick = { *//*TODO*//* })
    }
}*/


@Composable
@Preview
fun CategoryGridItemPreview() {
    VisitGenevaTheme {
        CategoryItem(
            itemType = ItemCardType.ListItem,
            category = LocalCategoriesProvider.defaultCategory,
            onCategoryClick = {}
        )
    }
}

@Composable
@Preview
fun CategoryGridPreview() {
    VisitGenevaTheme {
        CategoryGrid(
            categories = LocalCategoriesProvider.getCategories(),
            onCategoryClick = {},
        )
    }
}

@Composable
fun CategoryGrid(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.card_item_width)),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(6) {
            CategoryItem(
                itemType = ItemCardType.ListItem,
                category = LocalCategoriesProvider.defaultCategory,
                onCategoryClick = { /*TODO*/ },
                modifier = Modifier.width(IntrinsicSize.Min)
            )
        }
    }
}

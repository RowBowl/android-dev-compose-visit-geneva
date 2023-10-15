package com.example.visitgeneva.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.R
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.ItemCardType
import com.example.visitgeneva.utils.UiUtils
import com.example.visitgeneva.utils.drawCardBackground


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
fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = category.imageResId),
        contentDescription = "Category Grid Item",
        modifier = modifier
    )
}


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

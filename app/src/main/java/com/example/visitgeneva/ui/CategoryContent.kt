package com.example.visitgeneva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.ItemCardType
import com.example.visitgeneva.utils.UiUtils
import com.example.visitgeneva.utils.drawCardBackground


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun CategoryItem(
    itemType: ItemCardType,
    category: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    val mod: Modifier
    val brush: Brush

    when (itemType) {
        ItemCardType.ListItem -> {
            mod = Modifier
            brush = UiUtils.brush1
        }
        ItemCardType.DetailItem -> {
            mod = Modifier.fillMaxHeight()
            brush = UiUtils.brush2
        }
    }

    Card(
        modifier = modifier.shadow(elevation = 4.dp),
    ) {
        Box(
            modifier = mod
                .drawCardBackground(brush)
        ) {
            CategoryImage(
                category = category,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .alpha(0.85f)
            )
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = if (itemType == ItemCardType.ListItem) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.spacedBy(4.dp, alignment = Alignment.Top)
                }
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
fun CategoryGridItemListPreview() {
    VisitGenevaTheme {
        CategoryItem(
            itemType = ItemCardType.ListItem,
            category = LocalCategoriesProvider.defaultCategory,
            onCategoryClick = {},
            onBackPressed = {},
            modifier = Modifier.size(400.dp)
        ) 
    }
}

@Composable
@Preview
fun CategoryGridItemDetailPreview() {
    VisitGenevaTheme {
        CategoryItem(
            itemType = ItemCardType.DetailItem,
            category = LocalCategoriesProvider.defaultCategory,
            onCategoryClick = {},
            onBackPressed = {},
            modifier = Modifier
        )
    }
}




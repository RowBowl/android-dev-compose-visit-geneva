package com.example.visitgeneva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation
import com.example.visitgeneva.ui.components.CategoryImage
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.ItemCardType
import com.example.visitgeneva.utils.UiUtils
import com.example.visitgeneva.utils.drawCardBackground


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun CategoryItem(
    itemType: ItemCardType,
    category: Category,
    modifier: Modifier = Modifier,
    onItemClick: (Category) -> Unit,
    onRecClick: (Recommendation) -> Unit,
) {
    val mod: Modifier
    val brush: Brush

    when (itemType) {
        ItemCardType.ListItem -> {
            mod = Modifier.fillMaxWidth()
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
                .clickable(
                    enabled = true,
                    onClickLabel = "Go to details screen",
                    role = null,
                    onClick = { onItemClick(category) }
                )
        ) {
            CategoryImage(
                category = category,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .alpha(0.65f)
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
                            onRecClick = onRecClick,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CategoryListItemListPreview() {
    VisitGenevaTheme {
        CategoryItem(
            itemType = ItemCardType.ListItem,
            category = LocalCategoriesProvider.defaultCategory,
            modifier = Modifier.size(300.dp),
            onItemClick = { },
            onRecClick ={ }
        )
    }
}

@Composable
@Preview
fun CategoryListItemDetailPreview() {
    VisitGenevaTheme {
        CategoryItem(
            itemType = ItemCardType.DetailItem,
            category = LocalCategoriesProvider.defaultCategory,
            modifier = Modifier,
            onItemClick = { },
            onRecClick ={ }
        )

    }
}




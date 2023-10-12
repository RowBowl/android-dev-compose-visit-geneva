package com.example.visitgeneva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.R

@Composable
fun VisitGenevaApp() {
    Text(text = "Hello World, this is the Visit Geneva App")
}


@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.shadow(elevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(dimensionResource(id = R.dimen.card_item_width))//TODO move height/width values to resources
                .drawWithCache {
                    val brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF9E82F0),
                            Color(0xFF42A5F5)
                        )
                    )
                    onDrawBehind {
                        drawRoundRect(brush, cornerRadius = CornerRadius(10.dp.toPx()))
                    }
                },
            contentAlignment = Alignment.CenterStart

        ) {
            Image(
                painter = painterResource(id = category.iconResId),
                contentDescription = "Category Grid Item",
                modifier = Modifier
                    .alpha(0.85f)
                    .fillMaxSize()
                    .offset(
                        x = dimensionResource(id = R.dimen.card_item_width)*0.25f
                    )

            )
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
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
        }
    }
}

@Composable
@Preview
fun CategoryItemPreview() {
    VisitGenevaTheme {
        CategoryItem(
            category = LocalCategoriesProvider.defaultCategory,
            onCategoryClick = {})
    }
}


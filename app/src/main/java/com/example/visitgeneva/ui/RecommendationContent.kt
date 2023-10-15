package com.example.visitgeneva.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Recommendation
import com.example.visitgeneva.ui.theme.VisitGenevaTheme
import com.example.visitgeneva.utils.UiUtils


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
fun RecommendationItem(
    showFullDetail: Boolean,
    recommendation: Recommendation,
    onRecClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    val mod: Modifier
    val spacedBy: Dp
    val surfaceColors: Pair<Color, Color>
    val itemShape: Shape
    if (showFullDetail) {
        mod = modifier.fillMaxHeight()
        spacedBy = 40.dp
        surfaceColors = MaterialTheme.colorScheme.tertiary to
                MaterialTheme.colorScheme.onTertiary
        itemShape = MaterialTheme.shapes.medium
    }  else {
        mod = modifier
            .height(150.dp)
            .verticalScroll(scrollState)
        spacedBy = 10.dp
        surfaceColors = MaterialTheme.colorScheme.surfaceVariant to
                MaterialTheme.colorScheme.onSurfaceVariant
        itemShape = MaterialTheme.shapes.small

    }

    Surface (
        color = surfaceColors.first,
        contentColor = surfaceColors.second,
        border = BorderStroke(1.dp, UiUtils.brush2),
        shape = itemShape,
        modifier = mod
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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

@Composable
@Preview
fun RecommendationItemSmallPreview() {
    VisitGenevaTheme {
        RecommendationItem(
            recommendation = LocalCategoriesProvider.getFirstRecommendations().firstOrNull()!!,
            onRecClick = {},
            showFullDetail = false
        )
    }
}

@Composable
@Preview
fun RecommendationItemFullPreview() {
    VisitGenevaTheme {
        RecommendationItem(
            recommendation = LocalCategoriesProvider.getFirstRecommendations().firstOrNull()!!,
            onRecClick = {},
            showFullDetail = true
        )
    }
}
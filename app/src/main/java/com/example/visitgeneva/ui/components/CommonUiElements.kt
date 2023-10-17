package com.example.visitgeneva.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.visitgeneva.model.Category


@Composable
fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = category.imageResId),
        contentDescription = "Category Grid Item",
        modifier = modifier
    )
}
package com.example.visitgeneva.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object UiUtils {
    val brush1 = Brush.linearGradient(
        listOf(
            Color(0xFFF50057),
            Color(0xFFC00010)
        )
    )

    val brush2 = Brush.linearGradient(
        listOf(
            Color(0xFF6C63FF),
            Color(0xFF4C1A57)
        )
    )
}

enum class ItemCardType {
    ListItem,
    DetailItem
}

fun Modifier.drawCardBackground(brush: Brush): Modifier {
    return then(Modifier.drawWithCache {
        onDrawBehind {
            drawRoundRect(
                brush,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
    })
}
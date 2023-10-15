package com.example.visitgeneva.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

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
package com.example.visitgeneva.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visitgeneva.utils.ContentType

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
    }
}

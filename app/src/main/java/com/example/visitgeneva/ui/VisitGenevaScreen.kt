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
import com.example.visitgeneva.utils.ItemCardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitGenevaApp(windowSize: WindowSizeClass) {
    val contentType: ContentType
    val viewModel: VisitGenevaViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val listType: ListType

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            contentType = ContentType.GridAndDetail
            listType = ListType.Horizontal
        }
        WindowWidthSizeClass.Medium -> {
            contentType = ContentType.GridAndDetail
            listType = ListType.Vertical
        }
        WindowWidthSizeClass.Compact -> {
            contentType = ContentType.GridOnly
            listType = ListType.Vertical
        }
        else -> {
            contentType = ContentType.GridOnly
            listType = ListType.Vertical
        }
    }

    Scaffold (
        topBar = {

        }
    ){ innerPadding ->
        if(uiState.isShowingListPage) {
            CategoryList(
                categories = uiState.categoryList,
                contentPadding = innerPadding,
                listType = listType,
                onCategoryClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.navigateToDetailPage()
                }
            )
        } else {
            CategoryItem(
                itemType = ItemCardType.DetailItem,
                category = uiState.currentCategory,
                onCategoryClick = {},
                onBackPressed =  { viewModel.navigateToListPage() }
            )
        }
    }
}

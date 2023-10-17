package com.example.visitgeneva

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.visitgeneva.ui.CategoryItem
import com.example.visitgeneva.ui.CategoryList
import com.example.visitgeneva.ui.ListType
import com.example.visitgeneva.ui.RecommendationItem
import com.example.visitgeneva.ui.VisitGenevaViewModel
import com.example.visitgeneva.utils.ItemCardType


enum class VisitGenevaScreen {
    Categories,
    Recommendations,
    Details
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitGenevaApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold (
        topBar = {

        }
    ){ innerPadding ->
        val visitGenevaViewModel: VisitGenevaViewModel = viewModel()
        val uiState by visitGenevaViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = VisitGenevaScreen.Categories.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = VisitGenevaScreen.Categories.name) {
                CategoryList(
                    categories = uiState.categoryList,
                    listType = ListType.Vertical,
                    onCategoryClick = {
                        visitGenevaViewModel.updateCurrentCategory(it)
                        navController.navigate(VisitGenevaScreen.Recommendations.name)
                    },
                    onRecClick = {
                        visitGenevaViewModel.updateCurrentRecommendation(it)
                        navController.navigate(VisitGenevaScreen.Details.name)
                    }
                )
            }

            composable(route = VisitGenevaScreen.Recommendations.name) {
                CategoryItem(
                    itemType = ItemCardType.DetailItem,
                    category = uiState.currentCategory,
                    onItemClick = { cancelAndReset(viewModel = visitGenevaViewModel, navController = navController) },
                    onRecClick = { visitGenevaViewModel.updateCurrentRecommendation(it) }
                )
            }

            composable(route = VisitGenevaScreen.Details.name) {
                RecommendationItem(
                    showFullDetail = true,
                    recommendation = uiState.currentRecommendation,
                    onRecClick = {}
                )
            }
        }
    }
}

private fun cancelAndReset(viewModel: VisitGenevaViewModel, navController: NavHostController) {
    viewModel.resetUiState()
    navController.popBackStack(VisitGenevaScreen.Categories.name, inclusive = false)
}

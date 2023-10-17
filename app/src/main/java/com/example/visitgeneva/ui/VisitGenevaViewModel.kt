package com.example.visitgeneva.ui

import androidx.lifecycle.ViewModel
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import com.example.visitgeneva.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VisitGenevaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        VisitGenevaUiState(
            categoryList = LocalCategoriesProvider.getCategories(),
            currentCategory = LocalCategoriesProvider.getCategories().getOrElse(0) {
                LocalCategoriesProvider.defaultCategory
            },
            currentRecommendation = LocalCategoriesProvider.getCategories()
                .getOrElse(0) { LocalCategoriesProvider.defaultCategory }
                    .recommendations.getOrElse(0) {
                    Recommendation(-1,-1,-1,-1)
            }
        )
    )
    val uiState: StateFlow<VisitGenevaUiState> = _uiState.asStateFlow()

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(currentCategory = category)
        }
    }

    fun updateCurrentRecommendation(recommendation: Recommendation) {
        _uiState.update {
            it.copy(currentRecommendation = recommendation)
        }
    }

    fun resetUiState() {
        _uiState.value = VisitGenevaUiState()
    }
}

data class VisitGenevaUiState(
    val categoryList: List<Category> = LocalCategoriesProvider.getCategories(),
    val currentCategory: Category = LocalCategoriesProvider.defaultCategory,
    val isShowingListPage: Boolean = true,
    val currentRecommendation: Recommendation = currentCategory.recommendations[0]
)
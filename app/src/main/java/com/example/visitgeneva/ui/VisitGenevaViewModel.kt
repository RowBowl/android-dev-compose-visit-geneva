package com.example.visitgeneva.ui

import androidx.lifecycle.ViewModel
import com.example.visitgeneva.data.LocalCategoriesProvider
import com.example.visitgeneva.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VisitGenevaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        VisitGenevaUiState(
            categoryGrid = LocalCategoriesProvider.getCategories(),
            currentCategory = LocalCategoriesProvider.getCategories().getOrElse(0) {
                LocalCategoriesProvider.defaultCategory
            },
        )
    )
    val uiState: StateFlow<VisitGenevaUiState> = _uiState

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(currentCategory = category)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingGridPage = true)
        }
    }
    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingGridPage = false)
        }
    }
}

data class VisitGenevaUiState(
    val categoryGrid: List<Category> = emptyList(),
    val currentCategory: Category = LocalCategoriesProvider.defaultCategory,
    val isShowingGridPage: Boolean = true
)
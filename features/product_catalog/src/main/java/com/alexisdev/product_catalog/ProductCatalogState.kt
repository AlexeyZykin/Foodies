package com.alexisdev.product_catalog

import com.alexisdev.model.Category
import com.alexisdev.model.Meal

sealed interface ProductCatalogState
sealed interface CategoryUiState {
    data object Loading : CategoryUiState
    data class Error(val msg: String) : CategoryUiState
    data class Categories(
        val categories: List<Category> = emptyList(),
        val selectedCategory: String = "",
    ) : CategoryUiState
}

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Error(val msg: String) : ProductUiState
    data class Products(
        val totalPrice: Int = 0,
        val mealItemsState: List<MealItemState> = emptyList()
    ) : ProductUiState
}

data class MealItemState(
    val meal: Meal,
    val counter: Int = 0
)

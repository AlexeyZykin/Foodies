package com.alexisdev.product_catalog

import com.alexisdev.model.Meal

data class ProductCatalogState(
    val selectedCategory: String = "",
    val totalPrice: Int = 0,
    val mealItemsState: List<MealItemState> = emptyList(),
    val searchState: SearchState? = null
)

data class MealItemState(
    val meal: Meal,
    val counter: Int = 0
)

data class SearchState(
    val query: String = "",
)
package com.alexisdev.product_details

import com.alexisdev.model.Meal

sealed interface ProductDetailsState {
    data object Loading : ProductDetailsState
    data class Error(val msg: String) : ProductDetailsState
    data class Success(
        val meal: Meal,
        val counter: Int = 0,
    ) : ProductDetailsState
}


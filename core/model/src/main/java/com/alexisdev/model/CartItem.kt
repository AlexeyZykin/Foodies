package com.alexisdev.model

data class CartItem(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
    val price: Int,
    val quantity: Int
)
package com.alexisdev.product_catalog.presentation

data class MealUi(
    val idMeal: Int,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String,
    val strTags: String?
)

package com.alexisdev.products_api.model

data class MealDTO(
    val idMeal: Int,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String,
    val strTags: String?
)

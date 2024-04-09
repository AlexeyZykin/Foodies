package com.alexisdev.product_details.domain

data class Meal(
    val idMeal: Int,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String,
    val strTags: String?
)

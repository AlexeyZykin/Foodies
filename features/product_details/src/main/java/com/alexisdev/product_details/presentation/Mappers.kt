package com.alexisdev.product_details.presentation

import com.alexisdev.product_details.domain.Meal

fun Meal.toMealUi() = MealUi(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags
)
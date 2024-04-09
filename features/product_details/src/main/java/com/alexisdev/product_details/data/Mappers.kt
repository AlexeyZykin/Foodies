package com.alexisdev.product_details.data

import com.alexisdev.product_details.domain.Meal
import com.alexisdev.products_api.model.MealDTO


fun MealDTO.toMeal() = Meal(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags
)
package com.alexisdev.product_catalog.presentation

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Meal

fun Category.toCategoryUi() = CategoryUi(idCategory, strCategory)

fun Meal.toMealUi() = MealUi(
    idMeal = idMeal,
    strMeal =  strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags
)
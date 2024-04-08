package com.alexisdev.product_catalog.data.mapper

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Meal
import com.alexisdev.products_api.model.CategoryDTO
import com.alexisdev.products_api.model.MealDTO

fun CategoryDTO.toCategory() = Category(idCategory, strCategory)

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
package com.alexisdev.data

import com.alexisdev.database.model.CartItemEntity
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import com.alexisdev.model.Category
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

fun CartItem.toCartItemEntity() = CartItemEntity(
    idMeal = idMeal,
    strMeal = strMeal,
    strMealThumb = strMealThumb,
    price = price,
    quantity = quantity
)

fun CartItemEntity.toCartItem() = CartItem(
    idMeal = idMeal,
    strMeal = strMeal,
    strMealThumb = strMealThumb,
    price = price,
    quantity = quantity
)


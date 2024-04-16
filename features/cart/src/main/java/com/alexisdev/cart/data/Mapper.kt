package com.alexisdev.cart.data

import com.alexisdev.database.model.CartItemEntity
import com.alexisdev.model.CartItem

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
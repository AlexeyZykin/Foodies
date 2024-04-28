package com.alexisdev.cart

import com.alexisdev.model.CartItem

data class CartState(
    val totalPrice: Int = 0,
    val cartItems: List<CartItem> = listOf()
)
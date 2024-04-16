package com.alexisdev.data

import com.alexisdev.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(): Flow<List<CartItem>>
    fun addCart(cartItem: CartItem)
    fun removeCart(cartItem: CartItem)
}
package com.alexisdev.data

import com.alexisdev.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(): Flow<List<CartItem>>
    suspend fun addCart(cartItem: CartItem)
    suspend fun removeCart(cartItem: CartItem)
}
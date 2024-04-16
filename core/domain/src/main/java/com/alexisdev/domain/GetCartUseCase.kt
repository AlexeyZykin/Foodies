package com.alexisdev.domain

import com.alexisdev.data.CartRepository
import com.alexisdev.model.CartItem
import kotlinx.coroutines.flow.Flow

class GetCartUseCase(private val cartRepository: CartRepository) {
    fun invoke(): Flow<List<CartItem>> {
        return cartRepository.getCart()
    }
}
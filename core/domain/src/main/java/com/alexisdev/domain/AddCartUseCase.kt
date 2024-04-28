package com.alexisdev.domain

import com.alexisdev.data.CartRepository
import com.alexisdev.model.CartItem

class AddCartUseCase(private val cartRepository: CartRepository) {
    suspend fun invoke(cartItem: CartItem) {
        cartRepository.addCart(cartItem)
    }
}
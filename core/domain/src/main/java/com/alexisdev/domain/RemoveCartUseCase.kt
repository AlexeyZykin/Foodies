package com.alexisdev.domain

import com.alexisdev.data.CartRepository
import com.alexisdev.model.CartItem

class RemoveCartUseCase(private val cartRepository: CartRepository) {
    fun invoke(cartItem: CartItem) {
        cartRepository.removeCart(cartItem)
    }
}
package com.alexisdev.cart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.GetCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    var state by mutableStateOf(CartState())
        private set

    init {
        getCart()
    }

    private fun getCart() = viewModelScope.launch(Dispatchers.IO) {
        getCartUseCase.invoke().distinctUntilChanged().collect { cartItems ->
            var totalPrice = 0
            cartItems.forEach { totalPrice += it.price * it.quantity }
            state = state.copy(
                totalPrice = totalPrice,
                cartItems = cartItems
            )
        }
    }

    fun addCart(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        val updatedCartItem = cartItem.copy(quantity = cartItem.quantity + 1)
        addCartUseCase.invoke(updatedCartItem)
    }

    fun removeCart(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        val updatedCartItem = cartItem.copy(quantity = cartItem.quantity - 1)
        removeCartUseCase.invoke(updatedCartItem)
    }
}
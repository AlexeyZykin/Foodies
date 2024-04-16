package com.alexisdev.cart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.GetCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    private val _cart = MutableLiveData<List<CartItem>>()
    val cart: LiveData<List<CartItem>> get() = _cart

    init {
        getCart()
    }

    private fun getCart() = viewModelScope.launch {
        getCartUseCase.invoke().distinctUntilChanged().collect { cartItems ->
            _cart.postValue(cartItems)
        }
    }

    fun addCart(cartItem: CartItem) = viewModelScope.launch {

    }

    fun removeCart(cartItem: CartItem) = viewModelScope.launch {

    }
}
package com.alexisdev.product_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.FetchMealDetailsUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val fetchMealDetailsUseCase: FetchMealDetailsUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    var state by mutableStateOf(ProductDetailsState())
        private set

    fun fetchMealDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealDetailsUseCase.invoke(id).distinctUntilChanged().collect { meal ->
            state = state.copy(
                meal = meal
            )
        }
    }

    fun addMealToCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(
            counter = state.counter + 1
        )
        addCartUseCase.invoke(
            CartItem(
                idMeal = state.meal.idMeal,
                strMeal = state.meal.strMeal,
                strMealThumb = state.meal.strMealThumb,
                price = 550,
                quantity = state.counter
            )
        )
    }

    fun removeMealFromCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(
            counter = state.counter - 1
        )
        removeCartUseCase.invoke(
            CartItem(
                idMeal = state.meal.idMeal,
                strMeal = state.meal.strMeal,
                strMealThumb = state.meal.strMealThumb,
                price = 550,
                quantity = state.counter
            )
        )
    }
}
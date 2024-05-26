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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val fetchMealDetailsUseCase: FetchMealDetailsUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {

    private val _mealDetailsState: MutableStateFlow<ProductDetailsState> =
        MutableStateFlow(ProductDetailsState.Loading)
    val mealDetailsState = _mealDetailsState.asStateFlow()


    fun fetchMealDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealDetailsUseCase.invoke(id)
            .distinctUntilChanged()
            .catch { exception ->
                exception.message?.let {
                    _mealDetailsState.value = ProductDetailsState.Error(it)
                }
            }
            .collect { meal ->
                _mealDetailsState.value = ProductDetailsState.Success(meal = meal)
            }
    }

    fun addMealToCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        _mealDetailsState.update { state ->
            when (state) {
                is ProductDetailsState.Success -> {
                    val newCounter = state.counter + 1
                    addCartUseCase.invoke(
                        CartItem(
                            idMeal = state.meal.idMeal,
                            strMeal = state.meal.strMeal,
                            strMealThumb = state.meal.strMealThumb,
                            price = 550,
                            quantity = newCounter
                        )
                    )
                    state.copy(counter = newCounter)
                }

                else -> state
            }
        }
    }

    fun removeMealFromCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        _mealDetailsState.update { state ->
            when (state) {
                is ProductDetailsState.Success -> {
                    val newCounter = state.counter - 1
                    removeCartUseCase.invoke(
                        CartItem(
                            idMeal = state.meal.idMeal,
                            strMeal = state.meal.strMeal,
                            strMealThumb = state.meal.strMealThumb,
                            price = 550,
                            quantity = newCounter
                        )
                    )
                    state.copy(counter = newCounter)
                }

                else -> state
            }
        }
    }
}
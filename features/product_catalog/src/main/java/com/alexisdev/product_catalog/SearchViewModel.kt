package com.alexisdev.product_catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: com.alexisdev.domain.SearchUseCase,
    private val addCartUseCase: com.alexisdev.domain.AddCartUseCase,
    private val removeCartUseCase: com.alexisdev.domain.RemoveCartUseCase
) : ViewModel() {
    var state by mutableStateOf(ProductCatalogState())
        private set

    fun searchMeal(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUseCase.invoke(query).distinctUntilChanged().collect { listMeal ->
            val mealItemsState =  listMeal.map { meal ->
                MealItemState(meal = meal, counter = 0)
            }
            state = state.copy(
                mealItemsState = mealItemsState,
                totalPrice = 0,
            )
        }
    }

    fun addCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val updatedMealItemsState = state.mealItemsState.toMutableList()
        val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

        if (index != -1) {
            val updatedMealItemState = updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter + 1)
            updatedMealItemsState[index] = updatedMealItemState
        }

        state = state.copy(mealItemsState = updatedMealItemsState)
        addCartUseCase.invoke(
            //todo
            CartItem(
                idMeal = meal.idMeal,
                strMeal = meal.strMeal,
                strMealThumb = meal.strMealThumb,
                price = 550,
                quantity = 2
            )
        )
    }

    fun removeCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val updatedMealItemsState = state.mealItemsState.toMutableList()
        val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

        if (index != -1) {
            val updatedMealItemState = updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter - 1)
            updatedMealItemsState[index] = updatedMealItemState
        }

        state = state.copy(mealItemsState = updatedMealItemsState)
        removeCartUseCase.invoke(
            CartItem(
                idMeal = meal.idMeal,
                strMeal = meal.strMeal,
                strMealThumb = meal.strMealThumb,
                price = 550,
                quantity = 1
            )
        )
    }
}
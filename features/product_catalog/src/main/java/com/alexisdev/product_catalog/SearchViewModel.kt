package com.alexisdev.product_catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.domain.SearchUseCase
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {

    private val _searchUiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.EmptyQuery)
    val searchUiState = _searchUiState.asStateFlow()

    fun searchMeal(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUseCase.invoke(query).distinctUntilChanged().collect { listMeal ->
            val mealItemsState =  listMeal.map { meal ->
                MealItemState(meal = meal, counter = 0)
            }
            _searchUiState.value = SearchUiState.Success(
                mealItemsState = mealItemsState,
                totalPrice = 0
            )
        }
    }

    fun addCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val currentState = _searchUiState.value

        if (currentState is SearchUiState.Success) {
            val updatedMealItemsState = currentState.mealItemsState.toMutableList()
            val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

            if (index != -1) {
                val updatedMealItemState =
                    updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter + 1)
                updatedMealItemsState[index] = updatedMealItemState

                _searchUiState.value = currentState.copy(
                    mealItemsState = updatedMealItemsState,
                    totalPrice = currentState.totalPrice + 550
                )

                addCartUseCase.invoke(
                    CartItem(
                        idMeal = meal.idMeal,
                        strMeal = meal.strMeal,
                        strMealThumb = meal.strMealThumb,
                        price = 550,
                        quantity = updatedMealItemState.counter
                    )
                )
            }
        }
    }

    fun removeCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val currentState = _searchUiState.value

        if (currentState is SearchUiState.Success) {
            val updatedMealItemsState = currentState.mealItemsState.toMutableList()
            val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

            if (index != -1) {
                val updatedMealItemState =
                    updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter - 1)
                updatedMealItemsState[index] = updatedMealItemState

                _searchUiState.value = currentState.copy(
                    mealItemsState = updatedMealItemsState,
                    totalPrice = currentState.totalPrice - 550
                )

                removeCartUseCase.invoke(
                    CartItem(
                        idMeal = meal.idMeal,
                        strMeal = meal.strMeal,
                        strMealThumb = meal.strMealThumb,
                        price = 550,
                        quantity = updatedMealItemState.counter
                    )
                )
            }
        }
    }
}

sealed interface SearchUiState {
    data object Loading: SearchUiState
    data object EmptyQuery: SearchUiState
    data class Success(
        val mealItemsState: List<MealItemState> = emptyList(),
        val totalPrice: Int = 0,
        val query: String = "",
    ) : SearchUiState
}
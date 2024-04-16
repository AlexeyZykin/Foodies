package com.alexisdev.product_catalog.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import com.alexisdev.product_catalog.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals
    var state by mutableStateOf(ProductCatalogState())
        private set

    fun searchMeal(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUseCase.invoke(query).distinctUntilChanged().collect { listMeal ->
            Log.d("SearchViewModel", listMeal.toString())
            _meals.postValue(listMeal)
        }
    }

    fun addCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
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
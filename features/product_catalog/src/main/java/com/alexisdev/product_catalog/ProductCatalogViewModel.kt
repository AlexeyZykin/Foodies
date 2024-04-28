package com.alexisdev.product_catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.FetchMealsUseCase
import com.alexisdev.domain.GetCategoryListUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import com.alexisdev.model.Category
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProductCatalogViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val fetchMealsUseCase: FetchMealsUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories
    var state by mutableStateOf(ProductCatalogState())
        private set

    init {
        fetchCategoriesAndMeals()
    }

    private fun fetchCategoriesAndMeals() = viewModelScope.launch(Dispatchers.IO) {
        val categoryList = getCategoryListUseCase.invoke()
        _categories.postValue(categoryList)
        if (categoryList.isNotEmpty()) {
            fetchMealsByCategory(categoryList[0].strCategory)
            state = state.copy(
                selectedCategory = categoryList[0].strCategory
            )
        }
    }

    fun fetchMealsByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealsUseCase.invoke(category).distinctUntilChanged().collect { mealList ->
            val mealItemsState = mealList.map { meal ->
                MealItemState(meal = meal, counter = 0)
            }
            state = state.copy(
                mealItemsState = mealItemsState,
                totalPrice = 0
            )
        }
    }

    fun selectCategory(strCategory: String) = viewModelScope.launch {
        state = state.copy(
            selectedCategory = strCategory
        )
    }

    fun addCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val updatedMealItemsState = state.mealItemsState.toMutableList()
        val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

        if (index != -1) {
            val updatedMealItemState =
                updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter + 1)
            updatedMealItemsState[index] = updatedMealItemState
        }

        state = state.copy(
            mealItemsState = updatedMealItemsState,
            totalPrice = state.totalPrice + 550
        )

        addCartUseCase.invoke(
            //todo
            CartItem(
                idMeal = meal.idMeal,
                strMeal = meal.strMeal,
                strMealThumb = meal.strMealThumb,
                price = 550,
                quantity = 1
            )
        )
    }

    fun removeCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val updatedMealItemsState = state.mealItemsState.toMutableList()
        val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

        if (index != -1) {
            val updatedMealItemState =
                updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter - 1)
            updatedMealItemsState[index] = updatedMealItemState
        }

        state = state.copy(
            mealItemsState = updatedMealItemsState,
            totalPrice = state.totalPrice - 550
        )

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
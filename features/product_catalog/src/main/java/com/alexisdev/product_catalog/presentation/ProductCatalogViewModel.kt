package com.alexisdev.product_catalog.presentation

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
import com.alexisdev.model.Category
import com.alexisdev.model.Meal
import com.alexisdev.product_catalog.domain.usecase.FetchMealsUseCase
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
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
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals
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
            _meals.postValue(mealList)
        }
    }

    fun selectCategory(strCategory: String) = viewModelScope.launch {
        state = state.copy(
            selectedCategory = strCategory
        )
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
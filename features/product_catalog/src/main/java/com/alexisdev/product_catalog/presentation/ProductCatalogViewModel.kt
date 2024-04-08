package com.alexisdev.product_catalog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.product_catalog.domain.usecase.FetchMealsUseCase
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProductCatalogViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val fetchMealsUseCase: FetchMealsUseCase
) : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryUi>>()
    val categories: LiveData<List<CategoryUi>> get() = _categories
    private val _meals = MutableLiveData<List<MealUi>>()
    val meals: LiveData<List<MealUi>> get() = _meals

    init {
        fetchCategoriesAndMeals()
    }

    private fun fetchCategoriesAndMeals() = viewModelScope.launch(Dispatchers.IO) {
        val categoryList = getCategoryListUseCase.invoke().map { it.toCategoryUi() }
        _categories.postValue(categoryList)
        if (categoryList.isNotEmpty()) {
            fetchMealsByCategory(categoryList[0].strCategory)
        }
    }

    fun fetchMealsByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealsUseCase.invoke(category).distinctUntilChanged().collect { mealList ->
            _meals.postValue(
                mealList.map { meal ->
                    meal.toMealUi()
                }
            )
        }
    }
}
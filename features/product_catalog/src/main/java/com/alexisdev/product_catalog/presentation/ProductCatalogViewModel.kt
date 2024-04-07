package com.alexisdev.product_catalog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductCatalogViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryUi>>()
    val categories: LiveData<List<CategoryUi>> get() =_categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() = viewModelScope.launch(Dispatchers.IO) {
        _categories.postValue(
            getCategoryListUseCase.invoke().map { it.toCategoryUi() }
        )
    }

    fun fetchMealsByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {

    }
}
package com.alexisdev.product_catalog.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.product_catalog.domain.usecase.SearchUseCase
import com.alexisdev.product_catalog.presentation.model.MealUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _meals = MutableLiveData<List<MealUi>>()
    val meals: LiveData<List<MealUi>> get() = _meals

    fun searchMeal(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUseCase.invoke(query).distinctUntilChanged().collect { listMeal ->
            Log.d("SearchViewModel", listMeal.toString())
            _meals.postValue(
                listMeal.map { it.toMealUi() }
            )
        }
    }
}
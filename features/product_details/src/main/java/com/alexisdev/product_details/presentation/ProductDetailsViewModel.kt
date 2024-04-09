package com.alexisdev.product_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.product_details.domain.FetchMealDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val fetchMealDetailsUseCase: FetchMealDetailsUseCase
) : ViewModel() {
    private val _meal = MutableLiveData<MealUi>()
    val meal: LiveData<MealUi> get() = _meal

    fun fetchMealDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealDetailsUseCase.invoke(id).distinctUntilChanged().collect { meal ->
            _meal.postValue(meal.toMealUi())
        }
    }
}
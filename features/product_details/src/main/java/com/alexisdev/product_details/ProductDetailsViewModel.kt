package com.alexisdev.product_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val fetchMealDetailsUseCase: com.alexisdev.domain.FetchMealDetailsUseCase
) : ViewModel() {
    private val _meal = MutableLiveData<Meal>()
    val meal: LiveData<Meal> get() = _meal

    fun fetchMealDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealDetailsUseCase.invoke(id).distinctUntilChanged().collect { meal ->
            _meal.postValue(meal)
        }
    }
}
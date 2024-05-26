package com.alexisdev.product_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.common.Response
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.FetchMealsUseCase
import com.alexisdev.domain.GetCategoryListUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.model.CartItem
import com.alexisdev.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductCatalogViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val fetchMealsUseCase: FetchMealsUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : ViewModel() {
    private val _categoriesUiState: MutableStateFlow<CategoryUiState> = MutableStateFlow(CategoryUiState.Loading)
    val categoriesUiState = _categoriesUiState.asStateFlow()

    private val _productsUiState: MutableStateFlow<ProductUiState> = MutableStateFlow(ProductUiState.Loading)
    val productsUiState = _productsUiState.asStateFlow()

    init {
        fetchCategoriesAndMeals()
    }

   private fun fetchCategoriesAndMeals() = viewModelScope.launch(Dispatchers.IO) {
        getCategoryListUseCase.invoke()
            .distinctUntilChanged()
            .collect { response ->
                when (response) {
                    is Response.Success -> response.data?.let { list ->
                        _categoriesUiState.value = CategoryUiState.Categories(
                            categories = list,
                            selectedCategory = list[0].strCategory
                        )

                        fetchMealsByCategory(list[0].strCategory)
                    }

                    is Response.Loading -> _categoriesUiState.value = CategoryUiState.Loading

                    is Response.Error -> response.msg?.let {
                        _categoriesUiState.value = CategoryUiState.Error(it)
                    }
                }
            }
    }

    fun fetchMealsByCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchMealsUseCase.invoke(category)
            .distinctUntilChanged()
            .collect { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let { list ->
                            val mealItemsState = list.map { meal ->
                                MealItemState(meal = meal, counter = 0)
                            }
                            _productsUiState.value = ProductUiState.Products(
                                mealItemsState = mealItemsState
                            )
                        }
                    }

                    is Response.Loading -> _productsUiState.value = ProductUiState.Loading

                    is Response.Error -> response.msg?.let {
                        _productsUiState.value = ProductUiState.Error(it)
                    }
                }
            }

    }

    fun selectCategory(strCategory: String) = viewModelScope.launch {
        _categoriesUiState.update { state ->
            when (state) {
                is CategoryUiState.Categories -> state.copy(selectedCategory = strCategory)
                else -> state
            }
        }
    }

    fun addCart(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        val currentState = _productsUiState.value

        if (currentState is ProductUiState.Products) {
            val updatedMealItemsState = currentState.mealItemsState.toMutableList()
            val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

            if (index != -1) {
                val updatedMealItemState =
                    updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter + 1)
                updatedMealItemsState[index] = updatedMealItemState

                _productsUiState.value = currentState.copy(
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
        val currentState = _productsUiState.value

        if (currentState is ProductUiState.Products) {
            val updatedMealItemsState = currentState.mealItemsState.toMutableList()
            val index = updatedMealItemsState.indexOfFirst { it.meal == meal }

            if (index != -1) {
                val updatedMealItemState =
                    updatedMealItemsState[index].copy(counter = updatedMealItemsState[index].counter - 1)
                updatedMealItemsState[index] = updatedMealItemState

                _productsUiState.value = currentState.copy(
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
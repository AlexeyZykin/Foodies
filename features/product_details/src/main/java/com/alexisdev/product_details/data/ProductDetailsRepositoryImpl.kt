package com.alexisdev.product_details.data

import com.alexisdev.model.Meal
import com.alexisdev.product_details.domain.ProductDetailsRepository
import com.alexisdev.products_api.ProductsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductDetailsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductDetailsRepository {

    override fun fetchMealDetails(id: Int): Flow<Meal> = flow {
        val meal = productsApi.fetchMealDetails(id).meals?.get(0)
        if (meal != null) {
            emit(meal.toMeal())
        }
    }

}
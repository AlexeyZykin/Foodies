package com.alexisdev.data

import com.alexisdev.common.Response
import com.alexisdev.model.Meal
import com.alexisdev.model.Category
import kotlinx.coroutines.flow.Flow

interface ProductCatalogRepository {
    suspend fun getCategoryList(): Flow<Response<List<Category>>>
    fun fetchMealsCategory(category: String): Flow<Response<List<Meal>>>
    fun searchMeal(query: String): Flow<List<Meal>>
}
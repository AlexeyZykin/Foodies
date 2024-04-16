package com.alexisdev.product_catalog.domain.repository

import com.alexisdev.model.Meal
import com.alexisdev.model.Category
import kotlinx.coroutines.flow.Flow

interface ProductCatalogRepository {
    suspend fun getCategoryList(): List<Category>
    fun fetchMealsCategory(category: String): Flow<List<Meal>>
    fun searchMeal(query: String): Flow<List<Meal>>

}
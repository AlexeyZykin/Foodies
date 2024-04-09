package com.alexisdev.product_catalog.domain.repository

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface ProductCatalogRepository {
    suspend fun getCategoryList(): List<Category>
    fun fetchMealsCategory(category: String): Flow<List<Meal>>
    fun searchMeal(query: String): Flow<List<Meal>>

}
package com.alexisdev.product_catalog.data.repository

import com.alexisdev.product_catalog.data.mapper.toCategory
import com.alexisdev.product_catalog.data.mapper.toMeal
import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Meal
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import com.alexisdev.products_api.ProductsApi
import com.alexisdev.products_api.model.MealDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class ProductCatalogRepositoryImpl(private val productsApi: ProductsApi) :
    ProductCatalogRepository {
    override suspend fun getCategoryList(): List<Category> {
        return productsApi.fetchCategories().categories
            .map {
                it.toCategory()
            }
    }

    override fun fetchMealsCategory(category: String): Flow<List<Meal>> {
        return flow { emit(productsApi.fetchMealsByCategory(category).meals ?: emptyList<MealDTO>()) }
            .map { list ->
                list.map{ mealDto ->
                    mealDto.toMeal()
                }
            }
    }

    override fun searchMeal(query: String): Flow<List<Meal>> {
        return flow { emit(productsApi.searchMeal(query).meals ?: emptyList<MealDTO>()) }
            .map { listMeal ->
                listMeal.map { mealDto ->
                    mealDto.toMeal()
                }
            }

    }
}
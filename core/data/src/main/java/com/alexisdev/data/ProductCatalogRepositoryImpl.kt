package com.alexisdev.data

import com.alexisdev.common.Response
import com.alexisdev.model.Category
import com.alexisdev.model.Meal
import com.alexisdev.products_api.ProductsApi
import com.alexisdev.products_api.model.MealDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class ProductCatalogRepositoryImpl(private val productsApi: ProductsApi) :
    ProductCatalogRepository {
    override suspend fun getCategoryList(): Flow<Response<List<Category>>> = flow {
        emit(Response.Loading())

        val result = try {
            productsApi.fetchCategories().categories
        }
        catch (e: Exception) {
            emit(Response.Error(msg = e.message ?: "Error"))
            null
        }

        result?.let { list ->
            emit(
                Response.Success(
                    list.map { it.toCategory() }
                )
            )
        }
    }

    override fun fetchMealsCategory(category: String): Flow<Response<List<Meal>>> = flow {
        emit(Response.Loading())

        val result = try {
            productsApi.fetchMealsByCategory(category).meals ?: emptyList<MealDTO>()
        }
        catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error"))
            null
        }

        result?.let { list ->
            emit(
                Response.Success(
                    list.map { mealDto ->
                        mealDto.toMeal()
                    }
                )
            )
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
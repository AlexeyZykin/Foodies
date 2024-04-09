package com.alexisdev.products_api

import com.alexisdev.products_api.model.AllCategoriesDTO
import com.alexisdev.products_api.model.AllMealsDTO
import com.alexisdev.products_api.model.MealDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("categories.php")
    suspend fun fetchCategories(): AllCategoriesDTO

    @GET("filter.php?")
    suspend fun fetchMealsByCategory(
        @Query("c") category: String
    ) : AllMealsDTO

    @GET("search.php?")
    suspend fun searchMeal(
        @Query("s") query: String
    ) : AllMealsDTO

    @GET("lookup.php")
    suspend fun fetchMealDetails(
        @Query("i") id: Int
    ) : AllMealsDTO
}
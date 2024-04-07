package com.alexisdev.products_api

import com.alexisdev.products_api.model.AllCategoriesDTO
import retrofit2.http.GET

interface ProductsApi {
    @GET("categories.php")
    suspend fun fetchCategories(): AllCategoriesDTO
}
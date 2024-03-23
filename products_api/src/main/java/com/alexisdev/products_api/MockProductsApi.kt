package com.alexisdev.products_api

import com.alexisdev.products_api.model.CategoryDTO
import com.alexisdev.products_api.model.ProductDTO
import com.alexisdev.products_api.model.TagDTO
import ir.logicbase.mockfit.Mock
import retrofit2.http.GET
import retrofit2.http.Query

interface MockProductsApi {

    @Mock("Categories.json")
    @GET
    suspend fun fetchCategoryList(): List<CategoryDTO>

    @Mock("Tags.json")
    @GET
    suspend fun fetchTags(): List<TagDTO>

    @Mock("Products.json")
    @GET
    suspend fun fetchProducts(
        @Query("category") categoryId: Int,
        @Query("tags") tags: List<Int>
    ): List<ProductDTO>

    @Mock("Products.json")
    suspend fun searchProducts(
        @Query("q") query: String
    ): List<ProductDTO>
}
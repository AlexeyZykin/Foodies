package com.alexisdev.product_catalog.domain.repository

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Product
import com.alexisdev.product_catalog.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface ProductCatalogRepository {
    suspend fun fetchProducts(categoryId: Int, tagIds: List<Int>): Flow<List<Product>>
    suspend fun getCategoryList(): List<Category>
    suspend fun searchProducts(query: String): Flow<List<Product>>
    suspend fun getFilters(): List<Tag>
}
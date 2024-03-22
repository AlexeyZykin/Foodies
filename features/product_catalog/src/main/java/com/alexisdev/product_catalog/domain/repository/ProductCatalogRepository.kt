package com.alexisdev.product_catalog.domain.repository

interface ProductCatalogRepository {
    suspend fun fetchProducts()
    suspend fun getCategoryList()
}
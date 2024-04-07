package com.alexisdev.product_catalog.domain.repository

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductCatalogRepository {
    suspend fun getCategoryList(): List<Category>
}
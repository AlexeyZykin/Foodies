package com.alexisdev.domain

import com.alexisdev.data.ProductCatalogRepository
import com.alexisdev.model.Category

class GetCategoryListUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(): List<Category> {
        return productCatalogRepository.getCategoryList()
    }
}
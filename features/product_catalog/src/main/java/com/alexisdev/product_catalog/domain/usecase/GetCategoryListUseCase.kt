package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.model.Category
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository

class GetCategoryListUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(): List<Category> {
        return productCatalogRepository.getCategoryList()
    }
}
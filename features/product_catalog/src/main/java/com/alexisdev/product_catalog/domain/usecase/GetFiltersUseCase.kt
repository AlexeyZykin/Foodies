package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.product_catalog.domain.model.Tag
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository

class GetFiltersUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(): List<Tag> {
        return productCatalogRepository.getFilters()
    }
}
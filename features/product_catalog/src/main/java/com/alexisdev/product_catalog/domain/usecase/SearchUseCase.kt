package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository

class SearchUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(query: String) {

    }
}
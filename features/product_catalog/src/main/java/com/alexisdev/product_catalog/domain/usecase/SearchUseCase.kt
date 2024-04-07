package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.product_catalog.domain.model.Product
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(query: String) {

    }
}
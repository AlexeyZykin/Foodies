package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.product_catalog.domain.model.Meal
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(query: String): Flow<List<Meal>> {
        return productCatalogRepository.searchMeal(query)
    }
}
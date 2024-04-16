package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.model.Meal
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import kotlinx.coroutines.flow.Flow

class FetchMealsUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(category: String): Flow<List<Meal>> {
        return productCatalogRepository.fetchMealsCategory(category)
    }
}
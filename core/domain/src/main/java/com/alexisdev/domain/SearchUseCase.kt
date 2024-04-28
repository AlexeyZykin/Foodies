package com.alexisdev.domain

import com.alexisdev.data.ProductCatalogRepository
import com.alexisdev.model.Meal
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    fun invoke(query: String): Flow<List<Meal>> {
        return productCatalogRepository.searchMeal(query)
    }
}
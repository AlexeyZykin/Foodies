package com.alexisdev.domain

import com.alexisdev.common.Response
import com.alexisdev.data.ProductCatalogRepository
import com.alexisdev.model.Meal
import kotlinx.coroutines.flow.Flow

class FetchMealsUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    fun invoke(category: String): Flow<Response<List<Meal>>> {
        return productCatalogRepository.fetchMealsCategory(category)
    }
}
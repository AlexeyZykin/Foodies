package com.alexisdev.product_details.domain

import kotlinx.coroutines.flow.Flow

class FetchMealDetailsUseCase(private val productDetailsRepository: ProductDetailsRepository) {
    fun invoke(id: Int): Flow<Meal> {
        return productDetailsRepository.fetchMealDetails(id)
    }
}
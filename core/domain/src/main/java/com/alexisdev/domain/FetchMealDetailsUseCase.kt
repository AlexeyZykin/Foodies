package com.alexisdev.domain

import com.alexisdev.data.ProductDetailsRepository
import com.alexisdev.model.Meal
import kotlinx.coroutines.flow.Flow

class FetchMealDetailsUseCase(private val productDetailsRepository: ProductDetailsRepository) {
    fun invoke(id: Int): Flow<Meal> {
        return productDetailsRepository.fetchMealDetails(id)
    }
}
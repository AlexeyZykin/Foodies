package com.alexisdev.product_details.domain

import com.alexisdev.model.Meal
import kotlinx.coroutines.flow.Flow

interface ProductDetailsRepository {
    fun fetchMealDetails(id: Int): Flow<Meal>
}
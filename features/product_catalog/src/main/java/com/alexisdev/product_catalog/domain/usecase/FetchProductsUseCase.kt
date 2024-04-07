package com.alexisdev.product_catalog.domain.usecase

import com.alexisdev.product_catalog.domain.model.Product
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import kotlinx.coroutines.flow.Flow

class FetchProductsUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(categoryId: Int, tagIds: List<Int>) {

    }
}
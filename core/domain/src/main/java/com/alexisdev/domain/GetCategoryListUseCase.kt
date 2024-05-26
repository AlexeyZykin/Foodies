package com.alexisdev.domain

import com.alexisdev.common.Response
import com.alexisdev.data.ProductCatalogRepository
import com.alexisdev.model.Category
import kotlinx.coroutines.flow.Flow

class GetCategoryListUseCase(private val productCatalogRepository: ProductCatalogRepository) {
    suspend fun invoke(): Flow<Response<List<Category>>> {
        return productCatalogRepository.getCategoryList()
    }
}
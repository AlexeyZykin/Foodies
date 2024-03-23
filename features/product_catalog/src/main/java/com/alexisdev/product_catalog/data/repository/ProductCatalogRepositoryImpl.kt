package com.alexisdev.product_catalog.data.repository

import com.alexisdev.product_catalog.data.mapper.toCategory
import com.alexisdev.product_catalog.data.mapper.toProduct
import com.alexisdev.product_catalog.data.mapper.toTag
import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Product
import com.alexisdev.product_catalog.domain.model.Tag
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import com.alexisdev.products_api.MockProductsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProductCatalogRepositoryImpl(private val mockProductsApi: MockProductsApi) : ProductCatalogRepository {
    override suspend fun fetchProducts(categoryId: Int, tagIds: List<Int>): Flow<List<Product>> {
        return flow { emit(mockProductsApi.fetchProducts(categoryId, tagIds)) }
            .map { list ->
                list.map { it.toProduct() }
            }
    }

    override suspend fun getCategoryList(): List<Category> {
        return mockProductsApi.fetchCategoryList()
            .map { it.toCategory() }
    }

    override suspend fun searchProducts(query: String): Flow<List<Product>> {
        return flow { emit(mockProductsApi.searchProducts(query)) }
            .map { list ->
                list.map {  it.toProduct()  }
            }
    }

    override suspend fun getFilters(): List<Tag> {
        return mockProductsApi.fetchTags()
            .map { it.toTag() }
    }
}
package com.alexisdev.product_catalog.data.repository

import com.alexisdev.product_catalog.data.mapper.toCategory
import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import com.alexisdev.products_api.ProductsApi

class ProductCatalogRepositoryImpl(private val productsApi: ProductsApi) : ProductCatalogRepository {
    override suspend fun getCategoryList(): List<Category> {
        return productsApi.fetchCategories().categories.map {
            it.toCategory()
        }
    }

}
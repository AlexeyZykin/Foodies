package com.alexisdev.product_catalog.koin

import com.alexisdev.product_catalog.data.repository.ProductCatalogRepositoryImpl
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import com.alexisdev.product_catalog.domain.usecase.FetchProductsUseCase
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
import com.alexisdev.product_catalog.domain.usecase.GetFiltersUseCase
import com.alexisdev.product_catalog.domain.usecase.SearchUseCase
import org.koin.dsl.module

val featureProductCatalogModule = module {
    single<ProductCatalogRepository> { ProductCatalogRepositoryImpl(get()) }
    factory { FetchProductsUseCase(get()) }
    factory { GetCategoryListUseCase(get()) }
    factory { GetFiltersUseCase(get()) }
    factory { SearchUseCase(get()) }
}
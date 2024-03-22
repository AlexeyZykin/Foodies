package com.alexisdev.product_catalog.koin

import com.alexisdev.product_catalog.domain.usecase.FetchProductsUseCase
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
import org.koin.dsl.module

val featureProductCatalogModule = module {
    factory { FetchProductsUseCase() }
    factory { GetCategoryListUseCase() }
}
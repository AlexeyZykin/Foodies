package com.alexisdev.foodies.koin

import com.alexisdev.product_catalog.koin.featureProductCatalogModule
import com.alexisdev.products_api.koin.productsApiModule

val koinModules = listOf(
    featureProductCatalogModule,
    productsApiModule
)
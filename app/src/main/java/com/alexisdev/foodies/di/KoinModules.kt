package com.alexisdev.foodies.di

import com.alexisdev.product_catalog.di.featureProductCatalogModule
import com.alexisdev.product_details.di.productDetailsFeatureModule
import com.alexisdev.products_api.koin.productsApiModule

val koinModules = listOf(
    featureProductCatalogModule,
    productDetailsFeatureModule,
    productsApiModule
)
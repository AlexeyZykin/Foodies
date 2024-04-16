package com.alexisdev.foodies.di

import com.alexisdev.cart.di.cartFeatureModule
import com.alexisdev.database.di.databaseModule
import com.alexisdev.domain.di.coreDomainModule
import com.alexisdev.product_catalog.di.featureProductCatalogModule
import com.alexisdev.product_details.di.productDetailsFeatureModule
import com.alexisdev.products_api.koin.productsApiModule

val koinModules = listOf(
    featureProductCatalogModule,
    productDetailsFeatureModule,
    cartFeatureModule,
    productsApiModule,
    databaseModule,
    coreDomainModule
)
package com.alexisdev.data.di

import com.alexisdev.data.CartRepository
import com.alexisdev.data.CartRepositoryImpl
import com.alexisdev.data.ProductCatalogRepository
import com.alexisdev.data.ProductCatalogRepositoryImpl
import com.alexisdev.data.ProductDetailsRepository
import com.alexisdev.data.ProductDetailsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<CartRepository> { CartRepositoryImpl(get()) }
    single<ProductCatalogRepository> { ProductCatalogRepositoryImpl(get()) }
    single<ProductDetailsRepository> { ProductDetailsRepositoryImpl(get()) }
}
package com.alexisdev.product_catalog.di

import com.alexisdev.product_catalog.ProductCatalogViewModel
import com.alexisdev.product_catalog.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureProductCatalogModule = module {
    viewModel { ProductCatalogViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
}
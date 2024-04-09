package com.alexisdev.product_catalog.di

import com.alexisdev.product_catalog.data.repository.ProductCatalogRepositoryImpl
import com.alexisdev.product_catalog.domain.repository.ProductCatalogRepository
import com.alexisdev.product_catalog.domain.usecase.FetchMealsUseCase
import com.alexisdev.product_catalog.domain.usecase.GetCategoryListUseCase
import com.alexisdev.product_catalog.domain.usecase.SearchUseCase
import com.alexisdev.product_catalog.presentation.ProductCatalogViewModel
import com.alexisdev.product_catalog.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureProductCatalogModule = module {
    single<ProductCatalogRepository> { ProductCatalogRepositoryImpl(get()) }
    viewModel { ProductCatalogViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    factory { FetchMealsUseCase(get()) }
    factory { GetCategoryListUseCase(get()) }
    factory { SearchUseCase(get()) }
}
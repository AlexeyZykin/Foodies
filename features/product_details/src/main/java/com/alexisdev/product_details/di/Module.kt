package com.alexisdev.product_details.di

import com.alexisdev.product_details.data.ProductDetailsRepositoryImpl
import com.alexisdev.product_details.domain.FetchMealDetailsUseCase
import com.alexisdev.product_details.domain.ProductDetailsRepository
import com.alexisdev.product_details.presentation.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDetailsFeatureModule = module {
    single<ProductDetailsRepository> { ProductDetailsRepositoryImpl(get()) }
    factory { FetchMealDetailsUseCase(get()) }
    viewModel { ProductDetailsViewModel(get()) }
}
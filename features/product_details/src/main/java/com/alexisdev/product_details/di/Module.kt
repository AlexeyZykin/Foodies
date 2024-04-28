package com.alexisdev.product_details.di

import com.alexisdev.product_details.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDetailsFeatureModule = module {
    viewModel { ProductDetailsViewModel(get()) }
}
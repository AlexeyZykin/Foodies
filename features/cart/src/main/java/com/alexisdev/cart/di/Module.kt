package com.alexisdev.cart.di

import com.alexisdev.cart.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartFeatureModule = module {
    viewModel { CartViewModel(get(), get(), get()) }
}
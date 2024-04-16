package com.alexisdev.cart.di

import com.alexisdev.cart.presentation.CartViewModel
import com.alexisdev.cart.data.CartRepositoryImpl
import com.alexisdev.data.CartRepository
import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartFeatureModule = module {
    single<CartRepository> { CartRepositoryImpl(get()) }
    viewModel { CartViewModel(get(), get(), get()) }
}
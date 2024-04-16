package com.alexisdev.domain.di

import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.GetCartUseCase
import com.alexisdev.domain.RemoveCartUseCase
import org.koin.dsl.module

val coreDomainModule = module {
    factory { AddCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { RemoveCartUseCase(get()) }
}
package com.alexisdev.domain.di

import com.alexisdev.domain.AddCartUseCase
import com.alexisdev.domain.FetchMealDetailsUseCase
import com.alexisdev.domain.FetchMealsUseCase
import com.alexisdev.domain.GetCartUseCase
import com.alexisdev.domain.GetCategoryListUseCase
import com.alexisdev.domain.RemoveCartUseCase
import com.alexisdev.domain.SearchUseCase
import org.koin.dsl.module

val coreDomainModule = module {
    factory { AddCartUseCase(get()) }
    factory { FetchMealDetailsUseCase(get()) }
    factory { FetchMealsUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { GetCategoryListUseCase(get()) }
    factory { RemoveCartUseCase(get()) }
    factory { SearchUseCase(get()) }
}
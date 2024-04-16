package com.alexisdev.products_api.koin

import com.alexisdev.products_api.Config
import com.alexisdev.products_api.ProductsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val productsApiModule = module {
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single<ProductsApi> { provideProductsService(get()) }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

private fun provideProductsService(retrofit: Retrofit): ProductsApi {
    return retrofit.create(ProductsApi::class.java)
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

private fun provideInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY
    }
}

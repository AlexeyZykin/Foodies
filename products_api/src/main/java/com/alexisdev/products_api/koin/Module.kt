package com.alexisdev.products_api.koin

import android.content.Context
import android.util.Log
import com.alexisdev.products_api.Config
import com.alexisdev.products_api.MockProductsApi
import ir.logicbase.mockfit.MockFitInterceptor
import ir.logicbase.mockfit.MockPathRule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val productsApiModule = module {
    single { provideInterceptor(androidContext()) }
    single { provideOkhttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideProductsService(get()) }
}



private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

private fun provideProductsService(retrofit: Retrofit): MockProductsApi {
    return retrofit.create(MockProductsApi::class.java)
}

private fun provideOkhttpClient(mockFitInterceptor: MockFitInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(mockFitInterceptor)
        .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

private fun provideInterceptor(context: Context) = MockFitInterceptor(
bodyFactory = { input -> context.resources.assets.open(input) },
logger = { tag, message -> Log.d(tag, message) }, // pass logger to log events in logcat
baseUrl = Config.BASE_URL, // base url of your api
requestPathToMockPathRule = arrayOf(), // autogenerated constant, just press build button
mockFilesPath = Config.MOCK_FILES_PATH, // path to json files
mockFitEnable = true, // master setting to enable or disable mocking
apiEnableMock = true, // enable or disable mock when there are includes and excludes configs
apiIncludeIntoMock = arrayOf(), // include endpoint if `apiEnableMock` is false
apiExcludeFromMock = arrayOf(), // exclude endpoint if `apiEnableMock` is true
apiResponseLatency = 500L // latency of retrieving data
)
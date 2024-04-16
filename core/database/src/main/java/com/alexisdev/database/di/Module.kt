package com.alexisdev.database.di

import android.content.Context
import androidx.room.Room
import com.alexisdev.database.FoodiesDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideRoomDb(get()) }
    single { provideCartDao(get()) }
}


private fun provideRoomDb(context: Context): FoodiesDatabase {
    return Room.databaseBuilder(
        context,
        FoodiesDatabase::class.java,
        FoodiesDatabase.DATABASE_NAME
    ).build()
}

private fun provideCartDao(foodiesDatabase: FoodiesDatabase) = foodiesDatabase.getCartDao()
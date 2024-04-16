package com.alexisdev.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexisdev.database.dao.CartDao
import com.alexisdev.database.model.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1, exportSchema = false)
abstract class FoodiesDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao

    companion object {
        const val DATABASE_NAME = "foodies.db"
        const val CART_TABLE_NAME = "cart"
    }
}
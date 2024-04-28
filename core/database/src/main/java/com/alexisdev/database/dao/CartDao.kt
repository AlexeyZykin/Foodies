package com.alexisdev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alexisdev.database.FoodiesDatabase
import com.alexisdev.database.model.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM ${FoodiesDatabase.CART_TABLE_NAME}")
    fun getAll(): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItemEntity: CartItemEntity)

    @Delete
    suspend fun delete(cartItemEntity: CartItemEntity)

    @Update
    suspend fun update(cartItemEntity: CartItemEntity)
}
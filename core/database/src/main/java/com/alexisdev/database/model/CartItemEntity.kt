package com.alexisdev.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexisdev.database.FoodiesDatabase

@Entity(tableName = FoodiesDatabase.CART_TABLE_NAME)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("idMeal") val idMeal: Int,
    @ColumnInfo("strMeal") val strMeal: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("strMealThumb") val strMealThumb: String,
    @ColumnInfo("quantity") val quantity: Int
)

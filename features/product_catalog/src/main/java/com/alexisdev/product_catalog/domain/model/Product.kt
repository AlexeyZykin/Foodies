package com.alexisdev.product_catalog.domain.model

data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val measure: Int,
    val measureUnit: String,
    val energy_per_100_grams: Float,
    val proteins_per_100_grams: Float,
    val fats_per_100_grams: Float,
    val carbohydrates_per_100_grams: Float,
    val tagIds: List<Int>
)
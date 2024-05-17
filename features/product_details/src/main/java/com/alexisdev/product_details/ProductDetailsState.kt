package com.alexisdev.product_details

import com.alexisdev.model.Meal

data class ProductDetailsState(
    val meal: Meal = Meal(0, "", "", "", "", "", "", ""),
    val counter: Int = 0,
)
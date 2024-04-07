package com.alexisdev.product_catalog.presentation

import com.alexisdev.product_catalog.domain.model.Category

fun Category.toCategoryUi() = CategoryUi(idCategory, strCategory)
package com.alexisdev.product_catalog.data.mapper

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.products_api.model.CategoryDTO

fun CategoryDTO.toCategory() = Category(idCategory, strCategory)
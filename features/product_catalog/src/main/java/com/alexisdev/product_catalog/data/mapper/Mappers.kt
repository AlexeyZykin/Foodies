package com.alexisdev.product_catalog.data.mapper

import com.alexisdev.product_catalog.domain.model.Category
import com.alexisdev.product_catalog.domain.model.Product
import com.alexisdev.product_catalog.domain.model.Tag
import com.alexisdev.products_api.model.CategoryDTO
import com.alexisdev.products_api.model.ProductDTO
import com.alexisdev.products_api.model.TagDTO

fun CategoryDTO.toCategory() = Category(id, name)

fun TagDTO.toTag() = Tag(id, name)

fun ProductDTO.toProduct() = Product(
    id = id,
    categoryId = category_id,
    name = name,
    description = description,
    image = image,
    priceCurrent =price_current ,
    priceOld = price_old,
    measure = measure,
    measureUnit = measure_unit,
    energy_per_100_grams = energy_per_100_grams,
    proteins_per_100_grams = proteins_per_100_grams,
    fats_per_100_grams = fats_per_100_grams,
    carbohydrates_per_100_grams = carbohydrates_per_100_grams,
    tagIds = tag_ids
)
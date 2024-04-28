package com.alexisdev.product_catalog.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexisdev.core.ui.components.MealCategoryChip
import com.alexisdev.model.Category
import com.alexisdev.product_catalog.ProductCatalogState
import com.alexisdev.product_catalog.ProductCatalogViewModel

@Composable
fun MealCategories(
    categories: List<Category>,
    state: ProductCatalogState,
    viewModel: ProductCatalogViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { item ->
            val strCategory = item.strCategory
            MealCategoryChip(
                strCategory = strCategory,
                selectedCategory = state.selectedCategory
            ) {
                viewModel.selectCategory(strCategory)
                viewModel.fetchMealsByCategory(strCategory)
            }
        }
    }
}
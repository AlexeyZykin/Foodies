package com.alexisdev.product_catalog.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexisdev.core.ui.components.MealCategoryChip
import com.alexisdev.model.Category
import com.alexisdev.product_catalog.presentation.components.MealCategories
import com.alexisdev.product_catalog.presentation.components.MealList
import com.alexisdev.product_catalog.presentation.components.NavigateToCartButton

@Composable
fun ProductCatalogScreen(
    viewModel: ProductCatalogViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = viewModel.categories.observeAsState().value
    val meals = viewModel.meals.observeAsState().value
    val state = viewModel.state

    Surface(modifier = modifier) {
        Column {
            if (!categories.isNullOrEmpty()) {
                MealCategories(categories = categories, viewModel = viewModel, state = state)
            }
            if (!meals.isNullOrEmpty()) {
                MealList(
                    meals = meals,
                    state = state,
                    onMealIncrease = { meal ->
                        viewModel.addCart(meal)
                    },
                    onMealDecrease = { meal ->
                        viewModel.removeCart(meal)
                    },
                    onClickItem = onClickItem
                )
            }
        }
        NavigateToCartButton(onClick = onNavigateToCart)
    }
}

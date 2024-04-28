package com.alexisdev.product_catalog.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.alexisdev.product_catalog.ProductCatalogViewModel
import com.alexisdev.product_catalog.components.MealCategories
import com.alexisdev.product_catalog.components.MealList
import com.alexisdev.product_catalog.components.NavigateToCartButton

@Composable
fun ProductCatalogScreen(
    viewModel: ProductCatalogViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = viewModel.categories.observeAsState().value
    val state = viewModel.state

    Surface(modifier = modifier) {
        Column {
            if (!categories.isNullOrEmpty()) {
                MealCategories(categories = categories, viewModel = viewModel, state = state)
            }
            if (state.mealItemsState.isNotEmpty()) {
                MealList(
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
        NavigateToCartButton(
            state = state,
            onClick = onNavigateToCart
        )
    }
}

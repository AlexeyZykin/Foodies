package com.alexisdev.product_catalog.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alexisdev.product_catalog.CategoryUiState
import com.alexisdev.product_catalog.ProductCatalogViewModel
import com.alexisdev.product_catalog.ProductUiState
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
    val categoriesState: CategoryUiState by viewModel.categoriesUiState.collectAsState()
    val productsState: ProductUiState by viewModel.productsUiState.collectAsState()

    Surface(modifier = modifier) {
        Column {
            when (val state = categoriesState) {
                is CategoryUiState.Loading -> {}

                is CategoryUiState.Error -> {}

                is CategoryUiState.Categories ->
                    MealCategories(
                        categories = state.categories,
                        viewModel = viewModel,
                        selectedCategory = state.selectedCategory
                    )
            }

            when (val state = productsState) {
                is ProductUiState.Loading ->
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

                is ProductUiState.Error ->
                    Toast.makeText(LocalContext.current, state.msg, Toast.LENGTH_SHORT).show()

                is ProductUiState.Products ->
                    MealList(
                        meals = state.mealItemsState,
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
        when (val state = productsState) {
            is ProductUiState.Products ->
                NavigateToCartButton(
                    totalPrice = state.totalPrice,
                    onClick = onNavigateToCart
                )

            else -> {}
        }
    }
}

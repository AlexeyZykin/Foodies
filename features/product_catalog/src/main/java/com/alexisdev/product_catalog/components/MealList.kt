package com.alexisdev.product_catalog.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexisdev.model.Meal
import com.alexisdev.product_catalog.ProductCatalogState

@Composable
fun MealList(
    state: ProductCatalogState,
    onMealIncrease: (Meal) -> Unit,
    onMealDecrease: (Meal) -> Unit,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val meals = state.mealItemsState
    LazyColumn(modifier = modifier) {
        items(meals) { item ->
            MealItem(
                mealItemState = item,
                onMealIncrease = onMealIncrease,
                onMealDecrease = onMealDecrease,
                onClickItem = onClickItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
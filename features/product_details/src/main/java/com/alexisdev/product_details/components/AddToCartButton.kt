package com.alexisdev.product_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexisdev.core.R
import com.alexisdev.core.ui.components.CounterCard
import com.alexisdev.core.ui.components.FoodiesButton
import com.alexisdev.model.Meal
import com.alexisdev.product_details.ProductDetailsState

@Composable
fun AddToCartButton(
    state: ProductDetailsState,
    onFirstClick: () -> Unit,
    onNavigateToCart: () -> Unit,
    onMealIncrease: (Meal) -> Unit,
    onMealDecrease: (Meal) -> Unit,
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            if (state.counter != 0) {
                Row {
                    NavigateToCartButton(
                        onNavigateToCart,
                        Modifier.padding(8.dp)
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ) {
                        CounterCard(
                            count = state.counter,
                            onPlusClick = { onMealIncrease(state.meal) },
                            onMinusClick = { onMealDecrease(state.meal) }
                        )
                    }
                }
            } else {
                FoodiesButton(
                    label = "${stringResource(R.string.button_navigate_to_cart)} 550 ${
                        stringResource(id = R.string.rub_currency)
                    }",
                    onClick = onFirstClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
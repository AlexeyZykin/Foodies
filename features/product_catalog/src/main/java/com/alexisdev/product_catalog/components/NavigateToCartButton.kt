package com.alexisdev.product_catalog.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexisdev.core.ui.components.FoodiesButton
import com.alexisdev.product_catalog.ProductCatalogState
import com.alexisdev.core.R

@Composable
fun NavigateToCartButton(
    state: ProductCatalogState,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            if (state.totalPrice != 0) {
                FoodiesButton(
                    label = "${stringResource(R.string.button_navigate_to_cart)} ${state.totalPrice} ${
                        stringResource(id = R.string.rub_currency)}",
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
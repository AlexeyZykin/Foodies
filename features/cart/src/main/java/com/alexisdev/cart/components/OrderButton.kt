package com.alexisdev.cart.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexisdev.cart.CartState
import com.alexisdev.cart.R
import com.alexisdev.core.ui.components.FoodiesButton

@Composable
fun OrderButton(
    state: CartState,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            if (state.totalPrice != 0) {
                FoodiesButton(
                    label = "${stringResource(id = R.string.order_button)} ${state.totalPrice} ${
                        stringResource(id = R.string.rub_currency)
                    }",
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
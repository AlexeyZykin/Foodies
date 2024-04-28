package com.alexisdev.cart.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexisdev.cart.R
import com.alexisdev.core.ui.components.FoodiesButton

@Composable
fun EmptyCartButton(onClick: () -> Unit) {
    FoodiesButton(
        label = stringResource(id = R.string.empty_cart_button),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
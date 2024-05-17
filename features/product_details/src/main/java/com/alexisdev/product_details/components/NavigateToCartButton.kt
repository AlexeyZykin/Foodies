package com.alexisdev.product_details.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.alexisdev.core.ui.components.FoodiesButton
import com.alexisdev.core.R


@Composable
fun NavigateToCartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FoodiesButton(
        label = stringResource(R.string.navigate_to_cart_button),
        onClick = onClick,
        modifier = modifier
    )
}
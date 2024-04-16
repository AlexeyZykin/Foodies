package com.alexisdev.product_catalog.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexisdev.core.ui.components.FoodiesButton

@Composable
fun NavigateToCartButton(onClick: () -> Unit) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            FoodiesButton(
                label = "В корзину",
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
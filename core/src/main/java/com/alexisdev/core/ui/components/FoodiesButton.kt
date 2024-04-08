package com.alexisdev.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FoodiesButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = label)
    }
}

@Preview
@Composable
fun FoodiesChipPreview() {
    FoodiesButton(
        label = "В корзину",
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}
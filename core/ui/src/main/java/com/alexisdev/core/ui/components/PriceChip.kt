package com.alexisdev.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriceChip(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SuggestionChip(
        onClick = onClick,
        label = {
            Text(
                text = label,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        border = BorderStroke(0.dp, Color.Unspecified),
        modifier = modifier,
        shape = CircleShape,
        colors = ChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            labelColor = MaterialTheme.colorScheme.primary,
            leadingIconContentColor = Color.Unspecified,
            trailingIconContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            disabledLeadingIconContentColor = Color.Unspecified,
            disabledTrailingIconContentColor = Color.Unspecified
        )
    )
}
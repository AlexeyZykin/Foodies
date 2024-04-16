package com.alexisdev.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MealCategoryChip(
    strCategory: String,
    selectedCategory: String,
    onClick: () -> Unit
) {
    ElevatedFilterChip(
        selected = selectedCategory == strCategory,
        onClick = onClick,
        label = { Text(text = strCategory) },
        shape = CircleShape,
        modifier = Modifier.padding(horizontal = 6.dp),
        colors = SelectableChipColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            labelColor = Color.Gray,
            leadingIconColor = Color.Unspecified,
            trailingIconColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            disabledLeadingIconColor = Color.Unspecified,
            disabledSelectedContainerColor = Color.Unspecified,
            disabledTrailingIconColor = Color.Unspecified,
            selectedContainerColor = MaterialTheme.colorScheme.surface,
            selectedLabelColor = MaterialTheme.colorScheme.primary,
            selectedLeadingIconColor = Color.Unspecified,
            selectedTrailingIconColor = Color.Unspecified
        )
    )
}
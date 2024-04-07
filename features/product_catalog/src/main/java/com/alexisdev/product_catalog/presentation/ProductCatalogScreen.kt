package com.alexisdev.product_catalog.presentation

import android.health.connect.datatypes.MealType
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.alexisdev.product_catalog.domain.model.Product

@Composable
fun ProductCatalogScreen(
    viewModel: ProductCatalogViewModel,
    modifier: Modifier = Modifier
) {
    val categories = viewModel.categories.observeAsState().value
    Box(contentAlignment = Alignment.Center) {
        if (!categories.isNullOrEmpty())
            MealCategories(categories = categories, viewModel = viewModel)
    }

}

@Composable
fun MealCategories(categories: List<CategoryUi>, viewModel: ProductCatalogViewModel) {
    var selectedCategory by remember {
        mutableStateOf("")
    }
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { item ->
            MealCategoryChip(
                item,
                selectedCategory
            ) {
                selectedCategory = item.strCategory
                viewModel.fetchMealsByCategory(item.strCategory)
            }
        }
    }
}

@Composable
fun MealCategoryChip(
    item: CategoryUi,
    selectedCategory: String,
    onClick: () -> Unit
) {
    ElevatedFilterChip(
        selected = selectedCategory == item.strCategory,
        onClick = onClick,
        label = { Text(text = item.strCategory) },
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

package com.alexisdev.product_catalog.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alexisdev.core.ui.components.CounterCard
import com.alexisdev.core.ui.components.FoodiesButton
import com.alexisdev.core.ui.components.MealCategoryChip
import com.alexisdev.core.ui.components.PriceChip
import com.alexisdev.product_catalog.R

@Composable
fun ProductCatalogScreen(
    viewModel: ProductCatalogViewModel,
    modifier: Modifier = Modifier
) {
    val categories = viewModel.categories.observeAsState().value
    val meals = viewModel.meals.observeAsState().value
    var productCounter by remember {
        mutableIntStateOf(0)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            if (!categories.isNullOrEmpty()) {
                MealCategories(categories = categories, viewModel = viewModel)
            }
            if (!meals.isNullOrEmpty()) {
                MealList(meals, viewModel)
            }
        }
        Box(contentAlignment = Alignment.BottomCenter) {
            FoodiesButton(
                label = "В корзину",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

            }
        }
    }
}

@Composable
fun MealCategories(categories: List<CategoryUi>, viewModel: ProductCatalogViewModel) {
    var selectedCategory by remember {
        mutableStateOf(categories[0].strCategory)
    }
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { item ->
            val strCategory = item.strCategory
            MealCategoryChip(
                strCategory = strCategory,
                selectedCategory = selectedCategory
            ) {
                selectedCategory = strCategory
                viewModel.fetchMealsByCategory(strCategory)
            }
        }
    }
}

@Composable
fun MealList(
    meals: List<MealUi>,
    viewModel: ProductCatalogViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(meals) { item ->
            MealItem(
                mealUi = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun MealItem(mealUi: MealUi, modifier: Modifier = Modifier) {
    var counterState by remember {
        mutableIntStateOf(0)
    }
    ElevatedCard(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        modifier = modifier
    ) {
        Row {
            AsyncImage(
                model = mealUi.strMealThumb,
                contentDescription = stringResource(R.string.meal_image),
                placeholder = painterResource(id = R.drawable.ic_food_placeholder),
                modifier = Modifier
                    .weight(0.5f)
            )
            Column(
                modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mealUi.strMeal,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
                if (counterState == 0) {
                    PriceChip(
                        label = "550 р.",
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    ) {
                        counterState++
                    }
                } else {
                    CounterCard(
                        count = counterState,
                        onMinusClick = { counterState-- },
                        onPlusClick = { counterState++ },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    )
                }
            }

        }
    }
}

package com.alexisdev.product_catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alexisdev.core.ui.components.CounterCard
import com.alexisdev.core.ui.components.PriceChip
import com.alexisdev.model.Meal
import com.alexisdev.core.R
import com.alexisdev.product_catalog.MealItemState

@Composable
fun MealItem(
    mealItemState: MealItemState,
    onMealIncrease: (Meal) -> Unit,
    onMealDecrease: (Meal) -> Unit,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        onClick = { onClickItem(mealItemState.meal.idMeal) },
        modifier = modifier
    ) {
        Row {
            AsyncImage(
                model = mealItemState.meal.strMealThumb,
                contentDescription = stringResource(R.string.meal_image),
                placeholder = painterResource(id = R.drawable.ic_food_placeholder),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .weight(0.5f)
            )
            Column(
                modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mealItemState.meal.strMeal,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
                if (mealItemState.counter == 0) {
                    PriceChip(
                        label = "550 ${stringResource(id = R.string.rub_currency)}",
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    ) {
                        onMealIncrease(mealItemState.meal)
                    }
                } else {
                    CounterCard(
                        count = mealItemState.counter,
                        onMinusClick = {
                            onMealDecrease(mealItemState.meal)
                        },
                        onPlusClick = {
                            onMealIncrease(mealItemState.meal)
                        },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    )
                }
            }

        }
    }
}
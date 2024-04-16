package com.alexisdev.cart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.alexisdev.core.R
import com.alexisdev.core.ui.components.CounterCard
import com.alexisdev.model.CartItem


@Composable
fun CartItem(
    cartItem: CartItem,
    onMealIncrease: (CartItem) -> Unit,
    onMealDecrease: (CartItem) -> Unit,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var counterState by rememberSaveable {
        mutableIntStateOf(0)
    }
    ElevatedCard(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        onClick = { onClickItem(cartItem.idMeal) },
        modifier = modifier
    ) {
        Row(modifier = modifier) {
            AsyncImage(
                model = cartItem.strMealThumb,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_food_placeholder),
                modifier = Modifier
                    .weight(0.5f)
            )
            Column {
                Text(text = cartItem.strMeal)
                CounterCard(
                    count = 0,
                    onMinusClick = { onMealIncrease(cartItem) },
                    onPlusClick = { onMealDecrease(cartItem) }
                )
            }
            stringResource(id = R.string.rub_currency)
            Text(text = "${cartItem.price} ")
        }
    }
}
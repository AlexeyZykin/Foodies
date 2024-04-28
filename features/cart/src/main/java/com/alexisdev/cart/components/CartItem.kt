package com.alexisdev.cart.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = cartItem.strMealThumb,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_food_placeholder),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(0.3f)
            )
            Column(modifier = Modifier.weight(0.5f)) {
                Text(
                    text = cartItem.strMeal,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )
                CounterCard(
                    count = cartItem.quantity,
                    onMinusClick = { onMealIncrease(cartItem) },
                    onPlusClick = { onMealDecrease(cartItem) },
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .weight(0.2f)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "${cartItem.price} ${stringResource(id = R.string.rub_currency)}")
            }
        }
    }
}
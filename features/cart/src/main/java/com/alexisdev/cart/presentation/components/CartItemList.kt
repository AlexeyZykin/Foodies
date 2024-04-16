package com.alexisdev.cart.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexisdev.model.CartItem

@Composable
fun CartItemList(
    cartItems: List<CartItem>,
    onMealIncrease: (CartItem) -> Unit,
    onMealDecrease: (CartItem) -> Unit,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(cartItems) { cartItem ->
            CartItem(
                cartItem = cartItem,
                onMealIncrease = onMealIncrease,
                onMealDecrease = onMealDecrease,
                onClickItem = onClickItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
package com.alexisdev.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.alexisdev.cart.components.CartItemList
import com.alexisdev.cart.components.EmptyCartButton
import com.alexisdev.cart.components.OrderButton
import com.alexisdev.model.CartItem

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCheckout: () -> Unit,
    onClickEmptyCartButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cartItems = viewModel.state.cartItems
    val state = viewModel.state

    Surface(modifier = modifier) {
        if (cartItems.isNotEmpty()) {
            CartItemList(
                cartItems = cartItems,
                onMealIncrease = { cartItem ->
                    viewModel.addCart(cartItem)
                },
                onMealDecrease = { cartItem ->
                    viewModel.removeCart(cartItem)
                },
                onClickItem = onClickItem
            )
        } else {
            EmptyCartScreen(onClickEmptyCartButton)
        }

        OrderButton(
            state = state,
            onClick = onNavigateToCheckout
        )
    }
}


@Composable
fun EmptyCartScreen(onClickEmptyCartButton: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cart is empty",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Add what you like from the menu",
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Box(contentAlignment = Alignment.BottomCenter) {
        EmptyCartButton(onClickEmptyCartButton)
    }
}
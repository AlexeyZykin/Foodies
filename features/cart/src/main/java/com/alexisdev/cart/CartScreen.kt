package com.alexisdev.cart

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexisdev.cart.components.CartItemList
import com.alexisdev.cart.components.OrderButton

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCheckout: () -> Unit,
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
        }

        OrderButton(
            state = state,
            onClick = onNavigateToCheckout
        )
    }
}

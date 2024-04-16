package com.alexisdev.cart.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.alexisdev.cart.presentation.components.CartItemList

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val cartItems = viewModel.cart.observeAsState().value
    Surface(modifier = modifier) {
        if (cartItems != null) {
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
    }
}

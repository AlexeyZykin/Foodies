package com.alexisdev.foodies.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.alexisdev.foodies.navigation.Route
import com.alexisdev.foodies.navigation.navigateSingleTopTo

@Composable
fun AppBar(
    navController: NavHostController,
    currentScreen: Route
) {
    when (currentScreen) {
        is Route.Catalog, is Route.CatalogFilter -> CatalogTopBar(
            onCartClick = { navController.navigateSingleTopTo(Route.ShoppingCart.route) },
            onSearchActionClick = { navController.navigateSingleTopTo(Route.CatalogSearch.route) }
        )
        is Route.ShoppingCart -> DefaultTopBar(currentScreen = currentScreen) {
            navController.navigateUp()
        }
        else -> { }
    }
}

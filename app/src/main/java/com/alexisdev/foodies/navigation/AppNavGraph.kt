package com.alexisdev.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexisdev.product_catalog.presentation.CatalogSearchScreen
import com.alexisdev.product_catalog.presentation.FilterBottomSheet
import com.alexisdev.product_catalog.presentation.ProductCatalogScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Catalog.route,
        modifier = modifier
    ) {
        composable(route = Route.Catalog.route) {
            ProductCatalogScreen(

            )
        }

        composable(route = Route.CatalogSearch.route) {
            CatalogSearchScreen()
        }

        composable(route = Route.ProductDetails.route) {

        }

        composable(route = Route.ShoppingCart.route) {

        }
    }
}
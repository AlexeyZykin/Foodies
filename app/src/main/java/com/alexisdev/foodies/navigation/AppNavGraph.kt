package com.alexisdev.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexisdev.product_catalog.presentation.CatalogSearchScreen
import com.alexisdev.product_catalog.presentation.ProductCatalogScreen
import com.alexisdev.product_catalog.presentation.ProductCatalogViewModel
import org.koin.androidx.compose.koinViewModel

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
            val productCatalogViewModel = koinViewModel<ProductCatalogViewModel>()
            ProductCatalogScreen(
                productCatalogViewModel
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
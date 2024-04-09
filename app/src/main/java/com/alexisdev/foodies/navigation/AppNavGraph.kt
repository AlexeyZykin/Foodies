package com.alexisdev.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexisdev.product_catalog.presentation.SearchScreenContent
import com.alexisdev.product_catalog.presentation.ProductCatalogScreen
import com.alexisdev.product_catalog.presentation.ProductCatalogViewModel
import com.alexisdev.product_catalog.presentation.SearchViewModel
import com.alexisdev.splashscreen.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route,
        modifier = modifier
    ) {

        composable(route = Route.SplashScreen.route) {
            SplashScreen {
                navController.navigate(route = Route.Catalog.route)
            }
        }

        composable(route = Route.Catalog.route) {
            val productCatalogViewModel = koinViewModel<ProductCatalogViewModel>()
            ProductCatalogScreen(
                productCatalogViewModel
            )
        }

        composable(route = Route.CatalogSearch.route) {
            val searchViewModel = koinViewModel<SearchViewModel>()
            SearchScreenContent(viewModel = searchViewModel) {
                navController.navigateUp()
            }
        }

        composable(route = Route.ProductDetails.route) {

        }

        composable(route = Route.ShoppingCart.route) {

        }
    }
}
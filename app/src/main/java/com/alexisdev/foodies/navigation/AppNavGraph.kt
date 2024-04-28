package com.alexisdev.foodies.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.alexisdev.cart.CartScreen
import com.alexisdev.cart.CartViewModel
import com.alexisdev.product_catalog.screen.SearchScreen
import com.alexisdev.product_catalog.screen.ProductCatalogScreen
import com.alexisdev.product_catalog.ProductCatalogViewModel
import com.alexisdev.product_catalog.SearchViewModel
import com.alexisdev.product_details.ProductDetailsScreen
import com.alexisdev.product_details.ProductDetailsViewModel
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
                navController.navigate(route = Route.Catalog.route) {
                    popUpTo(Route.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Route.Catalog.route) {
            val productCatalogViewModel = koinViewModel<ProductCatalogViewModel>()
            ProductCatalogScreen(
                productCatalogViewModel,
                onClickItem = { mealId ->
                    navController.navigateSingleTopTo(route = "${Route.ProductDetails.route}/" + mealId)
                },
                onNavigateToCart = {
                    navController.navigateSingleTopTo(route = Route.ShoppingCart.route)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = Route.CatalogSearch.route) {
            val searchViewModel = koinViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel,
                onClickItem = { idMeal ->
                    navController.navigate("${Route.ProductDetails.route}/" + idMeal)
                },
                onBack = { navController.navigateUp() }
            )
        }

        composable(
            route = "${Route.ProductDetails.route}/{mealId}",
            arguments = listOf(
                navArgument("mealId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productDetailsViewModel = koinViewModel<ProductDetailsViewModel>()
            val mealId = requireNotNull(backStackEntry.arguments)
                .getInt("mealId")
            ProductDetailsScreen(mealId = mealId, viewModel = productDetailsViewModel) {
                navController.navigateUp()
            }
        }

        composable(route = Route.ShoppingCart.route) {
            val cartViewModel = koinViewModel<CartViewModel>()
            CartScreen(
                viewModel = cartViewModel,
                onClickItem = { idMeal ->
                    navController.navigate("${Route.ProductDetails.route}/" + idMeal)
                },
                onNavigateToCheckout = { },
                onClickEmptyCartButton = {
                    navController.navigate(Route.Catalog.route) {
                        launchSingleTop = true
                        popUpTo(Route.ShoppingCart.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
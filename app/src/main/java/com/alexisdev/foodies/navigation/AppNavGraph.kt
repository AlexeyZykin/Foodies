package com.alexisdev.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alexisdev.product_catalog.presentation.SearchScreenContent
import com.alexisdev.product_catalog.presentation.ProductCatalogScreen
import com.alexisdev.product_catalog.presentation.ProductCatalogViewModel
import com.alexisdev.product_catalog.presentation.SearchViewModel
import com.alexisdev.product_details.presentation.ProductDetailsScreen
import com.alexisdev.product_details.presentation.ProductDetailsViewModel
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
                productCatalogViewModel,
                { mealId ->
                    navController.navigate(route = "${Route.ProductDetails.route}/" + mealId)
                }
            )
        }

        composable(route = Route.CatalogSearch.route) {
            val searchViewModel = koinViewModel<SearchViewModel>()
            SearchScreenContent(
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

        }
    }
}
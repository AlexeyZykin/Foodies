package com.alexisdev.foodies.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
            onFilterClick = {  },
            onSearchActionClick = { navController.navigateSingleTopTo(Route.CatalogSearch.route) }
        )

        is Route.CatalogSearch -> CatalogSearch(navController)

        else -> DefaultTopBar(currentScreen = currentScreen)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsTopBar() {
    TopAppBar(
        title = { },
        navigationIcon = {
            Card(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewProductDetailsTopBar() {
    ProductDetailsTopBar()
}
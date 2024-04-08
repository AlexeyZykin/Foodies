package com.alexisdev.foodies.navigation

import com.alexisdev.foodies.R


sealed class Route(
    val route: String,
    val titleRes: Int?
) {
    data object SplashScreen: Route(route = "splash", titleRes = null)

    data object Catalog: Route(route = "catalog", titleRes = null)

    data object CatalogFilter: Route(route = "catalog_filter", titleRes = null)

    data object CatalogSearch: Route(route = "catalog_search", titleRes = null)

    data object ProductDetails: Route(route = "product_details", titleRes = R.string.title_product_details)

    data object ShoppingCart: Route(route = "shopping_cart", titleRes = R.string.title_shopping_cart)
}
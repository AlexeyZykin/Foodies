package com.alexisdev.foodies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexisdev.foodies.navigation.AppNavGraph
import com.alexisdev.foodies.components.AppBar
import com.alexisdev.foodies.navigation.foodiesScreens

@Composable
fun FoodiesApp(){
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = foodiesScreens.find { it.route == currentDestination?.route }
    Scaffold(
        topBar = {
            if (currentScreen != null) {
                AppBar(navController = navController, currentScreen = currentScreen)
            }
        },
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
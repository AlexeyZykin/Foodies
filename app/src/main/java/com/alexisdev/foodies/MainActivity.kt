package com.alexisdev.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexisdev.foodies.navigation.AppNavGraph
import com.alexisdev.core.ui.theme.FoodiesTheme
import com.alexisdev.foodies.navigation.Route
import com.alexisdev.foodies.components.AppBar
import com.alexisdev.foodies.components.BottomBar
import com.alexisdev.foodies.navigation.foodiesScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodiesTheme {
                FoodiesApp()
            }
        }
    }
}
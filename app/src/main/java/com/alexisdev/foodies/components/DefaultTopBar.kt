package com.alexisdev.foodies.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexisdev.foodies.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    currentScreen: Route
) {
    TopAppBar(
        title = {
            Text(
                text = if (currentScreen.titleRes != null)
                    stringResource(id = currentScreen.titleRes)
                else ""
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {

            }
        }

    )
}


@Preview
@Composable
fun DefaultTobBarPreview() {
    DefaultTopBar(currentScreen = Route.CatalogFilter)
}
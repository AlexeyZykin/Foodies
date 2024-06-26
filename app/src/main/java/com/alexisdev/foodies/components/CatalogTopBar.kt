package com.alexisdev.foodies.components

import androidx.compose.foundation.Image
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.alexisdev.foodies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogTopBar(
    onCartClick: () -> Unit,
    onSearchActionClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.foodies_logo),
                contentDescription = null
            )
        },
        navigationIcon = {
            IconButton(onClick = onCartClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchActionClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun CatalogTopBarPreview() {
    CatalogTopBar({}, {})
}
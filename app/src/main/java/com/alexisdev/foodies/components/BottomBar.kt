package com.alexisdev.foodies.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.alexisdev.foodies.R

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_catalog),
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.label_bottom_cart))
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.label_bottom_catalog))
            }
        )
    }
}
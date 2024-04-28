package com.alexisdev.core.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.alexisdev.core.R

@Composable
fun BackButton(onClick: () -> Unit) {
    ElevatedCard(
        shape = CircleShape,
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null
            )
        }
    }
}
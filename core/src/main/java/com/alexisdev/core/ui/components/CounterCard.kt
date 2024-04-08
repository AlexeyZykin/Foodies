package com.alexisdev.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexisdev.core.R

@Composable
fun CounterCard(
    count: Int,
    modifier: Modifier = Modifier,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit
) {
    Surface(modifier = modifier, shape = CircleShape) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onMinusClick,
                colors = IconButtonColors(
                    containerColor = Color.Unspecified,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }

            Text(
                text = count.toString(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            IconButton(
                onClick = onPlusClick,
                colors = IconButtonColors(
                    containerColor = Color.Unspecified,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun CounterCardPreview() {
    CounterCard(count = 1, onMinusClick = { /*TODO*/ }) {

    }
}
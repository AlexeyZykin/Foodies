package com.alexisdev.product_catalog.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexisdev.product_catalog.R

@Composable
fun ProductCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {

    }
}

@Composable
fun ProductCardContent(
    @DrawableRes imgDrawable: Int,
    //@StringRes productName: Int,
    productName: String,
    productWeight: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.tom_yam_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(170.dp)
        )
        Text(
            text = productName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        Text(
            text = productWeight,

            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = price,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(Modifier.size(width = 170.dp, height = 270.dp))
}


@Preview
@Composable
fun PreviewCardContent() {
    Surface(modifier = Modifier.width(170.dp)){
        ProductCardContent(
            imgDrawable = R.drawable.tom_yam_image,
            productName = "Том ям",
            productWeight = "500 г",
            price = "520 руб"
            )
    }
}
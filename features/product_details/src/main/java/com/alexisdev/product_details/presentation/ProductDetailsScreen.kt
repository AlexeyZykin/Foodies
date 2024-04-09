package com.alexisdev.product_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alexisdev.product_details.R

@Composable
fun ProductDetailsScreen(
    mealId: Int,
    viewModel: ProductDetailsViewModel,
    onBack: () -> Unit
) {
    viewModel.fetchMealDetails(mealId)
    val meal = viewModel.meal.observeAsState().value
    Surface(modifier = Modifier.fillMaxSize()) {
        if (meal != null) {
            ProductDetailsContent(
                meal,
                Modifier.verticalScroll(rememberScrollState())
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            BackButton(onBack)
        }
    }
}


@Composable
fun ProductDetailsContent(meal: MealUi, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = meal.strMealThumb,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_food_placeholder),
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = meal.strMeal,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Text(text = stringResource(R.string.details_category))
            Spacer(modifier = Modifier.width(8.dp))
            if (meal.strCategory != null)
                Text(text = meal.strCategory)
        }

        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = stringResource(R.string.details_tags))
            Spacer(modifier = Modifier.width(8.dp))
            if (meal.strTags != null)
                Text(text = meal.strTags)
        }

        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Text(text = stringResource(R.string.details_meal_area))
            Spacer(modifier = Modifier.width(8.dp))
            if (meal.strArea != null)
                Text(text = meal.strArea)
        }

        Text(
            text = stringResource(id = R.string.details_method_of_preparation),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp)
        )

        if (meal.strInstructions != null)
            Text(text = meal.strInstructions, Modifier.padding(horizontal = 8.dp))
    }
}


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
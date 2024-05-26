package com.alexisdev.product_details

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alexisdev.core.R
import com.alexisdev.core.ui.components.BackButton
import com.alexisdev.model.Meal
import com.alexisdev.product_details.components.AddToCartButton

@Composable
fun ProductDetailsScreen(
    mealId: Int,
    viewModel: ProductDetailsViewModel,
    onNavigateToCart: () -> Unit,
    onBack: () -> Unit
) {
    viewModel.fetchMealDetails(mealId)
    val mealDetailsState by viewModel.mealDetailsState.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        when (val state = mealDetailsState) {
            is ProductDetailsState.Loading -> {}

            is ProductDetailsState.Error ->
                Toast.makeText(LocalContext.current, state.msg, Toast.LENGTH_SHORT).show()

            is ProductDetailsState.Success -> {
                ProductDetailsContent(
                    state.meal,
                    Modifier.verticalScroll(rememberScrollState())
                )
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    BackButton(onBack)
                }
                AddToCartButton(
                    counter = state.counter,
                    meal = state.meal,
                    onFirstClick = { viewModel.addMealToCart(state.meal) },
                    onNavigateToCart = onNavigateToCart,
                    onMealIncrease = { viewModel.addMealToCart(state.meal) },
                    onMealDecrease = { viewModel.removeMealFromCart(state.meal) }
                )
            }
        }
    }
}


@Composable
fun ProductDetailsContent(meal: Meal, modifier: Modifier = Modifier) {
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

        DetailsPosition(
            subTitle = stringResource(R.string.details_category),
            data = meal.strCategory,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        DetailsPosition(
            subTitle = stringResource(R.string.details_tags),
            data = meal.strTags,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        DetailsPosition(
            subTitle = stringResource(R.string.details_meal_area),
            data = meal.strArea,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Text(
            text = stringResource(id = R.string.details_method_of_preparation),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp)
        )
        meal.strInstructions?.let {
            Text(text = it, Modifier.padding(horizontal = 8.dp))
        }
    }
}

@Composable
fun DetailsPosition(subTitle: String, data: String?, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = subTitle)
        Spacer(modifier = Modifier.width(8.dp))
        data?.let { Text(text = it) }
    }
}

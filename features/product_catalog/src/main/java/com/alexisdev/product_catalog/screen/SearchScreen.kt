package com.alexisdev.product_catalog.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.alexisdev.core.R
import com.alexisdev.product_catalog.SearchViewModel
import com.alexisdev.product_catalog.components.MealList
import com.alexisdev.product_catalog.components.NavigateToCartButton


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { SearchAppBar(onBack = onBack, viewModel = viewModel) }
    ) { innerPadding ->
        SearchScreenContent(
            viewModel = viewModel,
            onClickItem = onClickItem,
            onNavigateToCart = onNavigateToCart,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        )
    }
}

@Composable
fun SearchScreenContent(
    viewModel: SearchViewModel,
    onClickItem: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    Surface(modifier = modifier) {
        if (state.mealItemsState.isNotEmpty()) {
            MealList(
                state = state,
                onMealIncrease = { meal ->
                    viewModel.addCart(meal)
                },
                onMealDecrease = { meal ->
                    viewModel.removeCart(meal)
                },
                onClickItem = onClickItem
            )
        } else {
            EmptySearchContent()
        }
        NavigateToCartButton(
            state = state,
            onClick = onNavigateToCart
        )
    }
}

@Composable
fun EmptySearchContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.empty_search))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    onBack: () -> Unit,
    viewModel: SearchViewModel
) {
    var query by rememberSaveable { mutableStateOf("") }
    var showClearIcon by remember { mutableStateOf(false) }
    showClearIcon = query.isNotEmpty()

    TopAppBar(title = {
        TextField(
            value = query,
            onValueChange = { onQueryChanged ->
                query = onQueryChanged
                if (query.isNotEmpty()) {
                    viewModel.searchMeal(query.trim())
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            trailingIcon = {
                if (showClearIcon) {
                    IconButton(onClick = { query = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}
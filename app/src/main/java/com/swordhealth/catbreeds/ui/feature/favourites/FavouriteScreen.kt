package com.swordhealth.catbreeds.ui.feature.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swordhealth.catbreeds.ui.feature.BreedList
import com.swordhealth.catbreeds.ui.feature.LoadingBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun FavouritesScreen(
    showSnackbar: (String, SnackbarDuration) -> Unit,
    navController: NavController? = null,
    viewModel: FavouriteViewModel = hiltViewModel(),
    onNavigationRequested: (itemId: String) -> Unit
) {
    val effectFlow = viewModel.effects.receiveAsFlow()
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                is FavouriteContract.Effect.DataWasLoaded -> {
                    showSnackbar("Favourites are loaded.", SnackbarDuration.Short)
                }
                is FavouriteContract.Effect.Error -> {
                    showSnackbar("An error occurred.", SnackbarDuration.Short)
                }
                is FavouriteContract.Effect.NoDataConnection -> {
                    showSnackbar("No data connection.", SnackbarDuration.Short)
                }
            }
        }.collect()
    }
    Box {
        BreedList(navController, breeds = viewModel.state.breeds) {itemId ->
            onNavigationRequested(itemId)
        }
        when {
            viewModel.state.isLoading -> LoadingBar()
        }
        if(viewModel.state.isLoading)
            LoadingBar()
    }
}
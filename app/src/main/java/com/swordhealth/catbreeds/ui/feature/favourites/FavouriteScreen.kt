package com.swordhealth.catbreeds.ui.feature.favourites

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swordhealth.catbreeds.ui.feature.BreedList
import com.swordhealth.catbreeds.ui.feature.LoadingBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    navController: NavController? = null,
    viewModel: FavouriteViewModel = hiltViewModel(),
    onNavigationRequested: (itemId: String) -> Unit
) {
    val snackbarState = remember { SnackbarHostState() }
    val effectFlow = viewModel.effects.receiveAsFlow()
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                is FavouriteContract.Effect.DataWasLoaded -> {
                    snackbarState.showSnackbar(
                        message = "Favourites are loaded.",
                        duration = SnackbarDuration.Short
                    )
                }
                is FavouriteContract.Effect.Error -> {
                    snackbarState.showSnackbar(
                        message = "An error occurred.",
                        duration = SnackbarDuration.Short
                    )
                }
                is FavouriteContract.Effect.NoDataConnection -> {
                    snackbarState.showSnackbar(
                        message = "No data connection.",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }.collect()
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
    ) {
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
}
package com.swordhealth.catbreeds.ui.feature.breed_list

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
fun BreedListScreen(
    showSnackbar: (String, SnackbarDuration) -> Unit,
    navController: NavController? = null,
    viewModel: BreedViewModel = hiltViewModel(),
    onNavigationRequested: (itemId: String) -> Unit
){
    val effectFlow = viewModel.effects.receiveAsFlow()
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                /*is BreedContract.Effect.DataWasLoaded -> {
                    showSnackbar("Breeds are loaded.", SnackbarDuration.Short)
                }*/
                is BreedContract.Effect.Error -> {
                    showSnackbar("An error occurred.", SnackbarDuration.Short)
                }
                is BreedContract.Effect.NoDataConnection -> {
                    showSnackbar("No data connection.", SnackbarDuration.Short)
                }
                else -> {}
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
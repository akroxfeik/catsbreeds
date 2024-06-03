package com.swordhealth.catbreeds.ui.feature.favourites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swordhealth.catbreeds.ui.feature.BreedList
import com.swordhealth.catbreeds.ui.feature.LoadingBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    navController: NavController? = null,
    viewModel: FavouriteViewModel = hiltViewModel(),
    state: FavouriteContract.State,
    onNavigationRequested: (itemId: String) -> Unit
) {
    Scaffold() {
        Box {
            BreedList(navController, breeds = state.breeds) {itemId ->
                onNavigationRequested(itemId)
            }
            when {
                state.isLoading -> LoadingBar()
            }
            if(state.isLoading)
                LoadingBar()
        }
    }
}
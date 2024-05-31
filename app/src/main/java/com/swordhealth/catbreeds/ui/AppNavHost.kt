package com.swordhealth.catbreeds.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.ui.view.BreedDetailsScreen
import com.swordhealth.catbreeds.ui.feature.breed_list.BreedListScreen
import com.swordhealth.catbreeds.ui.feature.breed_list.BreedViewModel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.BreedList.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.BreedList.route) {
            val viewModel: BreedViewModel = hiltViewModel()
            BreedListScreen(
                navController = navController,
                state = viewModel.state,
                effectFlow = viewModel.effects.receiveAsFlow(),
                viewModel = viewModel,
                onNavigationRequested = { itemId ->
                    navController.navigate("${NavigationItem.BreedDetail.route}/${itemId}")
                }
            )
        }
        composable(
            route = NavigationItem.BreedDetail.route,
            arguments = listOf(navArgument("breed_id") {
                type = NavType.StringType
            })
        ) {
            BreedDetailsScreen(navController)
        }
    }
}


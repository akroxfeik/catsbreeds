package com.swordhealth.catbreeds.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.swordhealth.catbreeds.ui.feature.breed_details.BreedDetailsScreen
import com.swordhealth.catbreeds.ui.feature.breed_list.BreedListScreen
import com.swordhealth.catbreeds.ui.feature.favourites.FavouritesScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.BreedList.route,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.BreedList.route) {
            BreedListScreen(
                showSnackbar = showSnackbar,
                navController = navController,
                onNavigationRequested = { itemId ->
                    navController.navigate("${NavigationItem.BreedDetail.route}/${itemId}")
                }
            )
        }
        composable(
            route = NavigationItem.BreedDetail.route + "/{breed_id}",
            arguments = listOf(navArgument(Arg.BREED_ID) {
                type = NavType.StringType
                nullable = true
            })
        ) {
            BreedDetailsScreen(showSnackbar = showSnackbar)
        }
        composable(NavigationItem.Favourites.route) {
            FavouritesScreen(
                showSnackbar = showSnackbar,
                navController = navController,
                onNavigationRequested = { itemId ->
                    navController.navigate("${NavigationItem.BreedDetail.route}/${itemId}")
                }
            )
        }
    }
}


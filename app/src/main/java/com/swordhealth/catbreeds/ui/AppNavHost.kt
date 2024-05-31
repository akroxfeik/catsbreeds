package com.swordhealth.catbreeds.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.swordhealth.catbreeds.ui.view.BreedDetailsScreen
import com.swordhealth.catbreeds.ui.view.BreedListScreen

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
            BreedListScreen(navController)
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
package com.swordhealth.catbreeds.ui

object Arg {
    const val BREED_ID = "breed_id"
}

enum class Screen {
    BREED_LIST,
    BREED_DETAIL,
    FAVOURITES
}

sealed class NavigationItem(val route: String) {
    object BreedList : NavigationItem(Screen.BREED_LIST.name)
    object BreedDetail : NavigationItem(Screen.BREED_DETAIL.name)
    object Favourites : NavigationItem(Screen.FAVOURITES.name)
}

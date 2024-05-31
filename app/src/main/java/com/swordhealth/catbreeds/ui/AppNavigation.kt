package com.swordhealth.catbreeds.ui


enum class Screen {
    BREED_LIST,
    BREED_DETAIL
}

sealed class NavigationItem(val route: String) {
    object BreedList : NavigationItem(Screen.BREED_LIST.name)
    object BreedDetail : NavigationItem(Screen.BREED_DETAIL.name)
}

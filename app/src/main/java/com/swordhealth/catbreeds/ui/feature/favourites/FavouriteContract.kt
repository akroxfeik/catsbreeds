package com.swordhealth.catbreeds.ui.feature.favourites

import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.model.Favourite

class FavouriteContract {
    data class State(
        val favourites: List<Favourite> = listOf(),
        val breeds: List<Breed> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
        object NoDataConnection : Effect()
        object Error : Effect()
    }
}
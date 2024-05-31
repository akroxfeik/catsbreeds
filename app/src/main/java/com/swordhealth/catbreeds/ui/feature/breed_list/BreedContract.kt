package com.swordhealth.catbreeds.ui.feature.breed_list

import com.swordhealth.catbreeds.data.model.Breed

class BreedContract {
    data class State(
        val breeds: List<Breed> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
        object NoDataConnection : Effect()
        object Error : Effect()
    }
}
package com.swordhealth.catbreeds.ui.feature.breed_details

import com.swordhealth.catbreeds.data.model.Breed

class BreedDetailsContract {
    data class State(
        val breed: Breed? = null,
        val favourite: Boolean = true,
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
        object NoDataConnection : Effect()
        object Error : Effect()
    }
}
package com.swordhealth.catbreeds.ui.feature.breed_details

import com.swordhealth.catbreeds.data.model.Breed

class BreedDetailsContract {
    data class State(
        val breed: Breed? = null,
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}
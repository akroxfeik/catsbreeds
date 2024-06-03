package com.swordhealth.catbreeds.ui.feature.breed_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.model.RequestFavourite
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.ui.Arg
import com.swordhealth.catbreeds.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor (
    stateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    var state by mutableStateOf(
        BreedDetailsContract.State(
            breed = null,
            favourite = false,
            isLoading = true
        )
    )
        private set

    var effects = Channel<BreedDetailsContract.Effect>(UNLIMITED)

    init {
        val itemId = stateHandle.get<String>(Arg.BREED_ID)
        viewModelScope.launch { fetchBreed(itemId) }
    }

    private fun fetchBreed(itemId: String?) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getBreed(itemId!!).let {
                    if (it.isSuccessful) {
                        state = state.copy(breed = it.body(), isLoading = false)
                        effects.send(BreedDetailsContract.Effect.DataWasLoaded)
                    } else {
                        state = state.copy(isLoading = false)
                        effects.send(BreedDetailsContract.Effect.Error)
                    }
                }
            } else {
                state = state.copy(isLoading = false)
                effects.send(BreedDetailsContract.Effect.NoDataConnection)
            }
        }
    }

    fun onFavouriteClicked(item: Breed?) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                if (item?.favourite != null) {
                    mainRepository.removeFavourite(RequestFavourite(item.id, "aa"))
                    state = state.copy(favourite = false)
                } else if (item != null) {
                    mainRepository.addFavourite(RequestFavourite(item.id, "aa")).let {
                        if (it.isSuccessful) {
                            state = state.copy(favourite = true)
                        }
                    }
                }
            } else {
                state = state.copy(isLoading = false)
                effects.send(BreedDetailsContract.Effect.NoDataConnection)
            }
        }
    }
}
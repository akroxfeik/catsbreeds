package com.swordhealth.catbreeds.ui.feature.breed_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.ui.Arg
import com.swordhealth.catbreeds.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
            isLoading = true
        )
    )
        private set

    var effects = Channel<BreedDetailsContract.Effect>(Channel.UNLIMITED)

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
                    }// else effects.send(BreedDetailsContract.Effect.Error)
                }
            }// else effects.send(BreedDetailsContract.Effect.NoDataConnection)
        }
    }
}
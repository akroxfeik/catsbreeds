package com.swordhealth.catbreeds.ui.feature.breed_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    var state by mutableStateOf(
        BreedContract.State(
            breeds = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<BreedContract.Effect>(UNLIMITED)

    init {
        viewModelScope.launch { fetchBreeds() }
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            val isConnected = networkHelper.isNetworkConnected()
            val breedsResponse = if (isConnected) mainRepository.getBreeds() else null

            state = state.copy(
                breeds = breedsResponse?.body() ?: listOf(),
                isLoading = false
            )

            effects.send(
                when {
                    breedsResponse?.isSuccessful == true -> BreedContract.Effect.DataWasLoaded
                    isConnected -> BreedContract.Effect.Error
                    else -> BreedContract.Effect.NoDataConnection
                }
            )
        }
    }
}
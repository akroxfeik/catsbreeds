package com.swordhealth.catbreeds.ui.feature.favourites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    var state by mutableStateOf(
        FavouriteContract.State(
            breeds = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<FavouriteContract.Effect>(Channel.UNLIMITED)

    init {
        viewModelScope.launch { fetchFavourites() }
    }

    private fun fetchFavourites() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFavourites().let {
                    if (it.isSuccessful) {
                        state = state.copy(favourites = it.body() ?: listOf())
                        state.favourites.onEach { favourite ->
                            mainRepository.getBreed(favourite.sub_id).let {
                                if (it.isSuccessful) {
                                    val breed = it.body()!!.copy(favourite = favourite)
                                    state = state.copy(breeds = state.breeds + breed)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
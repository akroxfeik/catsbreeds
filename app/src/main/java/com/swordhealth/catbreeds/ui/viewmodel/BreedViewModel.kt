package com.swordhealth.catbreeds.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.data.repository.MainRepository
import com.swordhealth.catbreeds.utils.NetworkHelper
import com.swordhealth.catbreeds.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _breeds = MutableLiveData<Resource<List<Breed>>>()
    val breeds: LiveData<Resource<List<Breed>>>
        get() = _breeds

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            _breeds.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getBreeds().let {
                    if (it.isSuccessful) {
                        _breeds.postValue(Resource.success(it.body()))
                    } else _breeds.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _breeds.postValue(Resource.error("No internet connection", null))
        }
    }
}
/*
class BreedViewModel @ViewModelScoped constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _breeds = MutableLiveData<Resource<List<Breed>>>()
    val breeds: LiveData<Resource<List<Breed>>>
        get() = _breeds

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            _breeds.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getBreeds().let {
                    if (it.isSuccessful) {
                        _breeds.postValue(Resource.success(it.body()))
                    } else _breeds.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _breeds.postValue(Resource.error("No internet connection", null))
        }
    }
}*/
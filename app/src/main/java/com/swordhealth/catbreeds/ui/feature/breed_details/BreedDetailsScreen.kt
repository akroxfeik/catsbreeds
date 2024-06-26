package com.swordhealth.catbreeds.ui.feature.breed_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.swordhealth.catbreeds.data.model.Breed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun BreedDetailsScreen(
    showSnackbar: (String, SnackbarDuration) -> Unit,
    viewModel: BreedDetailsViewModel = hiltViewModel()) {
    val effectFlow = viewModel.effects.receiveAsFlow()
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                /*is BreedDetailsContract.Effect.DataWasLoaded -> {
                    showSnackbar("Breed details are loaded.", SnackbarDuration.Short)
                }*/
                is BreedDetailsContract.Effect.Error -> {
                    showSnackbar("An error occurred.", SnackbarDuration.Short)
                }
                is BreedDetailsContract.Effect.NoDataConnection -> {
                    showSnackbar( "No data connection.", SnackbarDuration.Short)
                }
                else -> {}
            }
        }.collect()
    }
    Box {
        BreedDetails(viewModel, viewModel.state.breed)
    }
}

@Composable
fun BreedDetails(viewModel: BreedDetailsViewModel, item: Breed?) {
    item?.let {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){
            Text(
                text = item.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp))
            Column(Modifier.fillMaxWidth()){
                SubcomposeAsyncImage(
                    model = "https://cdn2.thecatapi.com/images/${item.reference_image_id}.jpg",
                    contentDescription = "",
                    loading = {
                        CircularProgressIndicator(Modifier.size(25.dp))
                    },
                    contentScale = ContentScale.Crop,
                )
            }
            Text(text = "Origin: ${item.origin}")
            Text(text = "Temperament: ${item.temperament}")
            Text(text = "Description:\n${item.description}")
            favouriteButton(viewModel, item)
        }
    }
}

@Composable
fun favouriteButton(viewModel: BreedDetailsViewModel, item: Breed?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Button(
            onClick = {
                viewModel.onFavouriteClicked(item)
                //viewModel<BreedDetailsViewModel>().onFavouriteButtonClicked(item)
            },
            modifier = Modifier.weight(1f)
        ){
            Text(getFavouriteButtonText(item))
        }
    }
}

fun getFavouriteButtonText(item: Breed?): String {
    return if (item?.favourite != null) "Remove Favourite" else "Add Favourite"
}

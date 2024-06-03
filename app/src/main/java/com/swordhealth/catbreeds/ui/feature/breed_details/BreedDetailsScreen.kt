package com.swordhealth.catbreeds.ui.feature.breed_details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.swordhealth.catbreeds.data.model.Breed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreedDetailsScreen(
    viewModel: BreedDetailsViewModel = hiltViewModel(),
    state: BreedDetailsContract.State) {
    Scaffold () {
        Box {
            BreedDetails(state.breed)
        }
    }
}

@Composable
fun BreedDetails(breed: Breed?) {
    breed?.let {
        Column(Modifier.padding(10.dp).fillMaxWidth()){
            Text(
                text = breed.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp))
            Column(Modifier.fillMaxWidth()){
                Image(
                    painter = rememberImagePainter(
                        data = "https://cdn2.thecatapi.com/images/${breed.reference_image_id}.jpg"
                    ),
                    contentDescription = null,
                )
            }
            Text(text = "Origin: ${breed.origin}")
            Text(text = "Temperament: ${breed.temperament}")
            Text(text = "Description:\n${breed.description}")
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                Button(
                    onClick = {

                    },
                    modifier = Modifier.weight(1f)
                ){
                    Text("Add Favoutite")
                }
            }
        }
        // TODO e. A button to add/remove the breed from the favourites.
    }
}

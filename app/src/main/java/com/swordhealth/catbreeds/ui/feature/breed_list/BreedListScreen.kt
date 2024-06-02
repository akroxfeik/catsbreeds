package com.swordhealth.catbreeds.ui.feature.breed_list

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.swordhealth.catbreeds.data.model.Breed
import com.swordhealth.catbreeds.ui.feature.bottomButtons
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreedListScreen(
    navController: NavController? = null,
    viewModel: BreedViewModel = hiltViewModel(),
    state: BreedContract.State,
    onNavigationRequested: (itemId: String) -> Unit) {
    Scaffold() {
        Box {
            BreedList(navController, breeds = state.breeds) {itemId ->
                onNavigationRequested(itemId)
            }
            when {
                state.isLoading -> LoadingBar()
            }
            if(state.isLoading)
                LoadingBar()
        }
    }
}

@Composable
fun BreedList(
    navController: NavController? = null,
    breeds: List<Breed>,
    onItemClicked: (id: String) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(breeds) { item ->
                BreedItemRow(item = item, onItemClicked = onItemClicked)
            }
        }
        bottomButtons(navController)
    }
}

@Composable
fun BreedItemRow(
    item: Breed,
    onItemClicked: (id: String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .animateContentSize()
            .clickable { onItemClicked(item.id) }
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = rememberImagePainter(
                    data = "https://cdn2.thecatapi.com/images/${item.reference_image_id}.jpg"
                ),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = item.name,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 2
            )
        }
    }

}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
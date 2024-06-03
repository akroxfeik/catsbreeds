package com.swordhealth.catbreeds.ui.feature

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.swordhealth.catbreeds.data.model.Breed

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
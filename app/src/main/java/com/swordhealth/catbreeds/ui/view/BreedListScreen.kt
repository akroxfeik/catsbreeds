package com.swordhealth.catbreeds.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.swordhealth.catbreeds.data.model.Breed

@Composable
fun BreedListScreen(navController: NavController) {
    LazyColumn(){
        items(breeds) { item ->
            BreedItemRow(breed = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun BreedItemRow(
    item: Breed?,
    onItemClicked: (Breed) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .animateContentSize()
            .clickable { onItemClicked(item) }
    ) {
        Row (modifier = Modifier.animateContentSize()) {
            Image(
                painter = rememberImagePainter(
                    data = item.url
                ),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(
                    text = item?.name ?: "",
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
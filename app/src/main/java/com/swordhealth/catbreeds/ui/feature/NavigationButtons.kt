package com.swordhealth.catbreeds.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.swordhealth.catbreeds.ui.NavigationItem

@Composable
fun bottomButtons(navController: NavController? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            onClick = {
                navController?.navigate(NavigationItem.BreedList.route)
            },
            modifier = Modifier.weight(1f)
        ){
            Text("Breed List")
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(
            onClick = {
                navController?.navigate(NavigationItem.Favourites.route)
            },
            modifier = Modifier.weight(1f)
        ){
            Text("Favourites")
        }
    }
}
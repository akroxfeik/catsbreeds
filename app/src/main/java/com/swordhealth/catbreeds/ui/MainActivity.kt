package com.swordhealth.catbreeds.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.swordhealth.catbreeds.ui.feature.SnackbarAppState
import com.swordhealth.catbreeds.ui.feature.rememberSnackbarAppState
import com.swordhealth.catbreeds.ui.theme.CatbreedsTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatbreedsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appState: SnackbarAppState = rememberSnackbarAppState()
                    Scaffold(
                        snackbarHost = { SnackbarHost(appState.snackbarHostState) }
                    ){
                        AppNavHost(
                            navController = rememberNavController(),
                            showSnackbar = { message, duration ->
                                appState.showSnackbar(message, duration)
                            }
                        )
                    }
                }
            }
        }
    }
}

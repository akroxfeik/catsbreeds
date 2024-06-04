package com.swordhealth.catbreeds.ui.feature

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SnackbarAppState (
    val snackbarHostState: SnackbarHostState,
    val snackbarScope: CoroutineScope,
) {
    fun showSnackbar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        snackbarScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = duration
            )
        }
    }
}

@Composable
fun rememberSnackbarAppState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    snackbarScope: CoroutineScope = rememberCoroutineScope(),
) = remember(snackbarHostState, snackbarScope) {
    SnackbarAppState(
        snackbarHostState = snackbarHostState,
        snackbarScope = snackbarScope
    )
}
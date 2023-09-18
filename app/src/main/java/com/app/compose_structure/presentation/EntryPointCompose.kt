package com.app.compose_structure.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.compose_structure.presentation.navigation.DashboardDestination
import com.app.compose_structure.presentation.navigation.LoginDestination
import com.app.compose_structure.presentation.navigation.MyAppState
import com.app.compose_structure.presentation.navigation.MyNavHost
import com.app.compose_structure.presentation.navigation.rememberAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBaseApp(
    appState: MyAppState = rememberAppState(),
    isUserLoggedIn: Boolean
) {

    // Entry point
    Scaffold {
        MyNavHost(
            modifier = Modifier.padding(it),
            navController = appState.navController,
            startDestination = if (isUserLoggedIn) DashboardDestination.route else LoginDestination.route,
            navigateToDestination = appState::navigateToDestination,
            onBackPress = appState::onBackPress
        )
    }
}
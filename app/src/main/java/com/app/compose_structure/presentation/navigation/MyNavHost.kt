package com.app.compose_structure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.compose_structure.presentation.screens.DashBoardScreen
import com.app.compose_structure.presentation.screens.LoginScreenRoute

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    navigateToDestination: (INavigation, String) -> Unit,
    onBackPress: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // Screens

        composable(LoginDestination.route) {
            LoginScreenRoute(
                onBackPress = onBackPress,
                navigateToDashboardScreen = {
                    navigateToDestination(
                        DashboardDestination,
                        DashboardDestination.route
                    )
                }
            )
        }

        composable(DashboardDestination.route) {
            DashBoardScreen(

            )
        }

    }
}
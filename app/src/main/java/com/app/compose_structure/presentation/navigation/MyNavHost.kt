package com.app.compose_structure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.compose_structure.presentation.screens.DashBoardScreenRoute
import com.app.compose_structure.presentation.screens.LoginScreenRoute
import com.app.compose_structure.presentation.screens.SettingScreenRoute

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
                navigateToSettingScreen = {
                    navigateToDestination(
                        SettingDestination,
                        SettingDestination.route
                    )
                },
                navigateToDashboardScreen = {
                    navigateToDestination(
                        DashboardDestination,
                        DashboardDestination.route
                    )
                }
            )
        }

        composable(SettingDestination.route) {
            SettingScreenRoute(onBackPress = onBackPress)
        }

        composable(DashboardDestination.route) {
            DashBoardScreenRoute(
                onBackPress = onBackPress,
                navigateToReceptionOptionScreen = { /*TODO*/ },
                navigateToReceptionScreen = { /*TODO*/ },
                navigateToPutAwayScreen = { /*TODO*/ },
                navigateToExpeditionScreen = { /*TODO*/ },
                navigateToShippingScreen = { /*TODO*/ },
                navigateToReturnScreen = { /*TODO*/ },
                navigateToLocationTransferScreen = { /*TODO*/ },
                navigateToInventoryScreen = {},
                navigateToLoginScreen = {
                    navigateToDestination(
                        LoginDestination,
                        LoginDestination.route
                    )
                }
            )
        }
    }
}
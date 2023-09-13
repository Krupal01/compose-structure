package com.app.compose_structure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): MyAppState {
    return remember(navController) {
        MyAppState(navController)
    }
}

@Stable
class MyAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToDestination(
        destination: INavigation,
        route: String? = null
    ) {
        if(destination is DashboardDestination) {
            navController.navigate(route ?: destination.route) {
                popUpTo(0)
            }
        }else {
            navController.navigate(route ?: destination.route)
        }
    }

    fun onBackPress() {
        navController.popBackStack()
    }
}

package com.app.compose_structure.presentation.navigation

object LoginDestination : INavigation {
    override val route: String = "login_screen"
    override val destination: String = "login_destination"
}

object DashboardDestination : INavigation {
    override val route: String = "dashboard_screen"
    override val destination: String = "dashboard_destination"
}
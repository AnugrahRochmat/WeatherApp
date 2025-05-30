package anugrah.rochmat.weatherapp.presentation.navigation

sealed class NavigationRoutes(val route: String) {
    data object Weather : NavigationRoutes("weather")
}
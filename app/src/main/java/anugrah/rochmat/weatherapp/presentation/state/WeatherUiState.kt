package anugrah.rochmat.weatherapp.presentation.state

import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.entity.Weather

data class WeatherUiState(
    val isLoading: Boolean = false,
    val currentWeather: Weather? = null,
    val forecast: Forecast? = null,
    val error: String? = null,
    val searchQuery: String = "",
    val searchResults: List<String> = emptyList(),
    val isSearching: Boolean = false
)
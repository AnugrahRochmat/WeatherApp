package anugrah.rochmat.weatherapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import anugrah.rochmat.weather.R
import anugrah.rochmat.weatherapp.presentation.state.WeatherUiState
import anugrah.rochmat.weatherapp.presentation.ui.component.ForecastCard
import anugrah.rochmat.weatherapp.presentation.ui.component.WeatherCard
import anugrah.rochmat.weatherapp.presentation.ui.theme.WeatherAppTheme
import anugrah.rochmat.weatherapp.presentation.viewmodel.WeatherViewModel
import anugrah.rochmat.weatherapp.util.MockDataPreview
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.observeAsState()
    var showSearchDialog by remember { mutableStateOf(false) }

    WeatherScreenContent(
        uiState = uiState,
        onSearchClick = { showSearchDialog = true },
        onLocationClick = { viewModel.loadDefaultByLocation() },
        onRetry = {
            viewModel.clearError()
            viewModel.loadDefaultWeather()
        },
        showSearchDialog = showSearchDialog,
        onDismissSearch = { showSearchDialog = false },
        onCitySelected = { cityName ->
            viewModel.loadWeatherForCity(cityName)
            showSearchDialog = false
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreenContent(
    uiState: WeatherUiState?,
    onSearchClick: () -> Unit,
    onLocationClick: () -> Unit,
    onRetry: () -> Unit,
    showSearchDialog: Boolean,
    onDismissSearch: () -> Unit,
    onCitySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name)) },
            actions = {
                IconButton(onClick = onSearchClick) {
                    Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
                }
                IconButton(onClick = onLocationClick) {
                    Icon(Icons.Default.LocationOn, contentDescription = stringResource(R.string.current_location))
                }
            }
        )

        when {
            uiState?.isLoading == true -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState?.error != null -> {
                ErrorState(
                    error = uiState.error,
                    onRetry = onRetry
                )
            }

            uiState?.currentWeather != null -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 12.dp)
                        ) {
                            WeatherCard(weather = uiState.currentWeather)

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.forecast_5_day),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )
                        }
                    }

                    uiState.forecast?.let { forecast ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(forecast.forecasts) { forecastItem ->
                                ForecastCard(forecastItem = forecastItem)
                            }
                        }
                    }
                }
            }
        }
    }

    // Search Dialog
    if (showSearchDialog) {
        SearchDialog(
            onDismiss = onDismissSearch,
            onCitySelected = onCitySelected
        )
    }
}

@Composable
fun ErrorState(
    error: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun SearchDialog(
    onDismiss: () -> Unit,
    onCitySelected: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val cities = remember {
        listOf(
            "London", "New York", "Tokyo", "Paris", "Sydney", "Berlin",
            "Moscow", "Dubai", "Singapore", "Hong Kong", "Los Angeles",
            "Chicago", "Toronto", "Mumbai", "Delhi", "Cairo", "Rome",
            "Madrid", "Amsterdam", "Stockholm", "Copenhagen", "Oslo", "Jakarta", "Bekasi"
        )
    }

    val filteredCities = cities.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.search_city)) },
        text = {
            Column {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text(stringResource(R.string.city_name)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.heightIn(max = 200.dp)
                ) {
                    items(filteredCities) { city ->
                        TextButton(
                            onClick = { onCitySelected(city) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = city,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

// ============ PREVIEWS ============
@Preview(
    name = "Weather Screen - Loading Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WeatherScreenLoadingPreview() {
    WeatherAppTheme {
        WeatherScreenContent(
            uiState = WeatherUiState(isLoading = true),
            onSearchClick = {},
            onLocationClick = {},
            onRetry = {},
            showSearchDialog = false,
            onDismissSearch = {},
            onCitySelected = {}
        )
    }
}

@Preview(
    name = "Weather Screen - Success Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WeatherScreenSuccessPreview() {
    WeatherAppTheme {
        WeatherScreenContent(
            uiState = WeatherUiState(
                currentWeather = MockDataPreview.mockWeather,
                forecast = MockDataPreview.mockForecast
            ),
            onSearchClick = {},
            onLocationClick = {},
            onRetry = {},
            showSearchDialog = false,
            onDismissSearch = {},
            onCitySelected = {}
        )
    }
}

@Preview(
    name = "Weather Screen - Error Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WeatherScreenErrorPreview() {
    WeatherAppTheme {
        WeatherScreenContent(
            uiState = WeatherUiState(error = "Network connection failed"),
            onSearchClick = {},
            onLocationClick = {},
            onRetry = {},
            showSearchDialog = false,
            onDismissSearch = {},
            onCitySelected = {}
        )
    }
}

@Preview(
    name = "Weather Screen - Search Dialog Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun WeatherScreenSearchDialogPreview() {
    WeatherAppTheme {
        WeatherScreenContent(
            uiState = WeatherUiState(currentWeather = MockDataPreview.mockWeather),
            onSearchClick = {},
            onLocationClick = {},
            onRetry = {},
            showSearchDialog = true,
            onDismissSearch = {},
            onCitySelected = {}
        )
    }
}
package anugrah.rochmat.weatherapp.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import anugrah.rochmat.weatherapp.domain.entity.Weather
import anugrah.rochmat.weather.R
import anugrah.rochmat.weatherapp.util.MockDataPreview
import anugrah.rochmat.weatherapp.util.UiConstants
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun WeatherCard(weather: Weather) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // City and Country
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.humidity),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${weather.cityName}, ${weather.country}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Temperature
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherIcon(
                    iconCode = weather.icon,
                    contentDescription = stringResource(R.string.weather_icon)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.degree_celsius, weather.temperature),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            // Description
            Text(
                text = weather.description.replaceFirstChar { it.uppercase() },
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Weather Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDetailItem(
                    icon = Icons.Default.Thermostat,
                    label = stringResource(R.string.feels_like),
                    value = stringResource(R.string.degree_celsius, weather.feelsLike)
                )

                WeatherDetailItem(
                    icon = Icons.Default.WaterDrop,
                    label = stringResource(R.string.humidity),
                    value = stringResource(R.string.humid_percentage, weather.humidity)
                )

                WeatherDetailItem(
                    icon = Icons.Default.Air,
                    label = stringResource(R.string.wind),
                    value = stringResource(R.string.wind_speed, weather.windSpeed)
                )

                WeatherDetailItem(
                    icon = Icons.Default.Compress,
                    label = stringResource(R.string.pressure),
                    value = stringResource(R.string.pressure_hpa, weather.pressure)
                )
            }
        }
    }
}

@Composable
fun WeatherDetailItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun WeatherIcon(
    iconCode: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 60.dp
) {
    val imageUrl = UiConstants.getWeatherIconUrl(iconCode)

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_weather_placeholder),
        error = painterResource(R.drawable.ic_weather_placeholder)
    )
}


// ============ PREVIEWS ============
@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun WeatherCardLightPreview() {
    MaterialTheme {
        WeatherCard(MockDataPreview.mockWeather)
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WeatherCardDarkPreview() {
    MaterialTheme {
        WeatherCard(MockDataPreview.mockWeather)
    }
}
package anugrah.rochmat.weatherapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import anugrah.rochmat.weather.R
import anugrah.rochmat.weatherapp.domain.entity.ForecastItem
import anugrah.rochmat.weatherapp.util.DateUtil
import anugrah.rochmat.weatherapp.util.MockDataPreview

@Composable
fun ForecastCard(forecastItem: ForecastItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Day
            Column (
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = DateUtil.formatDateFromUnix(forecastItem.dateUnix),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = forecastItem.description.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Time
            Column {
                Text(
                    text = DateUtil.formatTimeFromUnix(forecastItem.dateUnix),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Weather Icon
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                WeatherIcon(
                    iconCode = forecastItem.icon,
                    contentDescription = stringResource(R.string.weather_icon),
                    size = 40.dp
                )
            }

            // Temperature
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.forecast_temp_max, forecastItem.maxTemp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.forecast_temp_min, forecastItem.minTemp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }

                // Humidity
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Water,
                        contentDescription = stringResource(R.string.humidity),
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.humid_percentage, forecastItem.humidity),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

// ============ PREVIEWS ============
@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun ForestCardPreview() {
    MaterialTheme {
        ForecastCard(MockDataPreview.mockForecastItem)
    }
}
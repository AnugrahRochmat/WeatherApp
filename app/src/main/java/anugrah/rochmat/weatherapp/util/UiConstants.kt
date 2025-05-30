package anugrah.rochmat.weatherapp.util

object UiConstants {
    private const val WEATHER_ICON_BASE_URL = "https://openweathermap.org/img/wn/"
    private const val WEATHER_ICON_SIZE_2X = "@2x.png"

    fun getWeatherIconUrl(iconCode: String): String {
        return "$WEATHER_ICON_BASE_URL$iconCode$WEATHER_ICON_SIZE_2X"
    }
}
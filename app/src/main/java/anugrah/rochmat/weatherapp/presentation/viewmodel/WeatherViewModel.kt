package anugrah.rochmat.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import anugrah.rochmat.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import anugrah.rochmat.weatherapp.domain.usecase.GetForecastUseCase
import anugrah.rochmat.weatherapp.domain.usecase.GetWeatherByLocationUseCase
import anugrah.rochmat.weatherapp.presentation.state.WeatherUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase,
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData(WeatherUiState())
    val uiState: LiveData<WeatherUiState> = _uiState

    init {
        loadDefaultWeather()
    }

    fun loadDefaultWeather() {
        loadWeatherForCity(DEFAULT_CITY)
    }

    fun loadDefaultByLocation() {
        loadWeatherByLocation(DEFAULT_LAT, DEFAULT_LON)
    }

    fun loadWeatherForCity(cityName: String) {
        _uiState.value = _uiState.value?.copy(isLoading = true, error = null)

        val weatherDisposable = getCurrentWeatherUseCase(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weather ->
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        currentWeather = weather,
                        error = null
                    )
                    loadForecast(cityName)
                },
                { error ->
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error occurred"
                    )
                }
            )

        compositeDisposable.add(weatherDisposable)
    }

    fun loadWeatherByLocation(lat: Double, lon: Double) {
        _uiState.value = _uiState.value?.copy(isLoading = true, error = null)

        val weatherDisposable = getWeatherByLocationUseCase(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weather ->
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        currentWeather = weather,
                        error = null
                    )
                    loadForecast(weather.cityName)
                },
                { error ->
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to get location weather"
                    )
                }
            )

        compositeDisposable.add(weatherDisposable)
    }

    private fun loadForecast(cityName: String) {
        val forecastDisposable = getForecastUseCase(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { forecast ->
                    _uiState.value = _uiState.value?.copy(forecast = forecast)
                },
                {}
            )

        compositeDisposable.add(forecastDisposable)
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value?.copy(searchQuery = query)
    }

    fun clearError() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val DEFAULT_CITY = "Bekasi"
        private const val DEFAULT_LAT = -6.2349
        private const val DEFAULT_LON = 106.9896
    }
}
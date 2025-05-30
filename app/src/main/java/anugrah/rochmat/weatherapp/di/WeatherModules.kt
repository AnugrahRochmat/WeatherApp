package anugrah.rochmat.weatherapp.di

import anugrah.rochmat.weatherapp.data.datasource.local.WeatherLocalDataSource
import anugrah.rochmat.weatherapp.data.datasource.local.WeatherLocalDataSourceImpl
import anugrah.rochmat.weatherapp.data.datasource.remote.WeatherRemoteDataSource
import anugrah.rochmat.weatherapp.data.datasource.remote.WeatherRemoteDataSourceImpl
import anugrah.rochmat.weatherapp.data.repository.WeatherRepositoryImpl
import anugrah.rochmat.weatherapp.domain.repository.WeatherRepository
import anugrah.rochmat.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import anugrah.rochmat.weatherapp.domain.usecase.GetForecastUseCase
import anugrah.rochmat.weatherapp.domain.usecase.GetWeatherByLocationUseCase
import anugrah.rochmat.weatherapp.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherDataSourceModule = module {
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single<WeatherLocalDataSource> { WeatherLocalDataSourceImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}

val weatherBusinessModule = module {
    single { GetCurrentWeatherUseCase(get()) }
    single { GetWeatherByLocationUseCase(get()) }
    single { GetForecastUseCase(get()) }
    viewModel { WeatherViewModel(get(), get(), get()) }
}

val weatherModules = listOf(
    weatherDataSourceModule,
    weatherBusinessModule
)
package anugrah.rochmat.weatherapp

import android.app.Application
import anugrah.rochmat.weatherapp.di.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(coreModules)
        }
    }
}
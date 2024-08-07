package uz.xml.mytaxiapp

import android.app.Application
import com.mapbox.common.MapboxOptions
import org.koin.core.context.startKoin
import uz.xml.mytaxiapp.data.di.MainModule

class MyTaxiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MapboxOptions.accessToken =
            "sk.eyJ1IjoieG1sMjIiLCJhIjoiY2x6amVzZ3F5MHE2YjJrczFoY3c1aXozZSJ9.I7yhwf2FobtA3N_KVqch-A"

        startKoin {
            modules(listOf(MainModule))
        }
    }
}
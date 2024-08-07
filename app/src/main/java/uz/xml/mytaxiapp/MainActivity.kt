package uz.xml.mytaxiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import uz.xml.mytaxiapp.presentation.compose.MapView
import uz.xml.mytaxiapp.ui.theme.MyTaxiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mapViewportState = rememberMapViewportState {
                setCameraOptions {
                    center(Point.fromLngLat(69.2401, 41.2995))
                    zoom(10.0)
                    pitch(0.0)
                }
            }
            MyTaxiAppTheme {
                MapView(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState,
                    moveToCurrentLocation = {},
                    zoomIn = {},
                    zoomOut = {},
                )
            }
        }
    }
}
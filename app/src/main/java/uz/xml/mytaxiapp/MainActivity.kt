package uz.xml.mytaxiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.xml.mytaxiapp.presentation.MainViewModel
import uz.xml.mytaxiapp.presentation.MyTaxiViewEvents
import uz.xml.mytaxiapp.presentation.compose.AppUi
import uz.xml.mytaxiapp.ui.theme.MyTaxiAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModel<MainViewModel>()
            val cameraOptionsState by viewModel.cameraOptions.collectAsState()

            val mapViewportState = rememberMapViewportState {
                cameraOptionsState?.let(::setCameraOptions)
            }

            MyTaxiAppTheme {
                AppUi(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState.apply {
                        cameraOptionsState?.let(::setCameraOptions)
                    },
                    moveToCurrentLocation = {},
                    zoomIn = { viewModel.setEvent(MyTaxiViewEvents.ZoomIn(mapViewportState.cameraState?.zoom)) },
                    zoomOut = { viewModel.setEvent(MyTaxiViewEvents.ZoomOut(mapViewportState.cameraState?.zoom)) },
                )
            }
        }
    }
}
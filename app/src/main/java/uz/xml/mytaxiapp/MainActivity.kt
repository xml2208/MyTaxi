package uz.xml.mytaxiapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.xml.mytaxiapp.presentation.MainViewModel
import uz.xml.mytaxiapp.presentation.MyTaxiViewEvents
import uz.xml.mytaxiapp.presentation.compose.AppUi
import uz.xml.mytaxiapp.ui.theme.MyTaxiAppTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModel<MainViewModel>()
            val cameraOptionsState by viewModel.cameraOptions.collectAsState()
            val mapViewportState =
                rememberMapViewportState { cameraOptionsState?.let(::setCameraOptions) }
            val currentLocationState = viewModel.viewState.value.currentLocation
            var isCurrentLocationButtonClicked by remember { mutableStateOf(false) }
            val bottomSheetState = rememberStandardBottomSheetState(confirmValueChange = { it != SheetValue.Hidden})
            val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
            val sheetState by rememberUpdatedState(scaffoldState.bottomSheetState)

            MyTaxiAppTheme {
                AppUi(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState.apply { cameraOptionsState?.let(::setCameraOptions) },
                    moveToCurrentLocation = {
                        isCurrentLocationButtonClicked = !isCurrentLocationButtonClicked
                        viewModel.setEvent(MyTaxiViewEvents.MoveToCurrentLocation(this))
                    },
                    zoomIn = { viewModel.setEvent(MyTaxiViewEvents.ZoomIn(mapViewportState.cameraState?.zoom)) },
                    zoomOut = { viewModel.setEvent(MyTaxiViewEvents.ZoomOut(mapViewportState.cameraState?.zoom)) },
                    currentLocation = Point.fromLngLat(
                        currentLocationState.longitude,
                        currentLocationState.latitude
                    ),
                    isNeededToMoveCurrentLocation = isCurrentLocationButtonClicked,
                    scaffoldState = scaffoldState,
                    showMapControls = sheetState.currentValue == SheetValue.PartiallyExpanded,
                    style = if(isSystemInDarkTheme()) Style.TRAFFIC_NIGHT else Style.TRAFFIC_DAY
                )
            }
        }
    }
}
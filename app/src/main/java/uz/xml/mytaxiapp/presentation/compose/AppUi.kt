package uz.xml.mytaxiapp.presentation.compose
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppUi(
    mapViewportState: MapViewportState,
    moveToCurrentLocation: () -> Unit,
    zoomIn: () -> Unit,
    zoomOut: () -> Unit,
    currentLocation: Point,
    modifier: Modifier = Modifier,
    style: String = Style.TRAFFIC_DAY,
    isNeededToMoveCurrentLocation: Boolean,
    scaffoldState: BottomSheetScaffoldState,
    showMapControls: Boolean,
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = { MapBottomSheet( modifier = Modifier.navigationBarsPadding()) }
                ,
                sheetShape = RoundedCornerShape(0.dp),
                sheetContainerColor = Color.Transparent,
                sheetShadowElevation = 0.dp,
                sheetPeekHeight = 140.dp,
                sheetDragHandle = null,
                content = {}
            )
        }
    ) {
        Box(modifier = modifier) {
            MapView(
                modifier = Modifier.navigationBarsPadding().statusBarsPadding(),
                mapViewportState = mapViewportState,
                style = style,
                currentLocation = currentLocation,
                isNeededToMoveCurrentLocation = isNeededToMoveCurrentLocation,
            )
            MapTopBar(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 48.dp)
            )
            AnimatedVisibility(
                visible = showMapControls,
                modifier = Modifier.align(Alignment.Center)
            ) {
                MapControlButtons(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    moveToCurrentLocation = moveToCurrentLocation,
                    zoomIn = zoomIn,
                    zoomOut = zoomOut,
                )
            }
        }
    }
}
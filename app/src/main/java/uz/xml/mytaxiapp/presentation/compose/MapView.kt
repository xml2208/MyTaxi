package uz.xml.mytaxiapp.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.style.MapStyle
import uz.xml.mytaxiapp.R

@Composable
internal fun MapView(
    modifier: Modifier,
    mapViewportState: MapViewportState,
    moveToCurrentLocation: () -> Unit,
    zoomIn: () -> Unit,
    zoomOut: () -> Unit,
    style: String = Style.TRAFFIC_DAY,
) {
    Box(modifier = modifier) {
        MapboxMap(
            modifier = Modifier,
            mapViewportState = mapViewportState,
            style = { MapStyle(style = style) }
        )
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MapControlButton(
                modifier = Modifier
                    .background(color = colorResource(R.color.chevron_bcg), shape = RoundedCornerShape(14.dp)),
                icon = R.drawable.ic_chevrons, iconTint = R.color.map_control_button_gray,
                onClick = { }
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MapControlButton(
                    icon = R.drawable.ic_plus,
                    iconTint = R.color.map_control_button_gray,
                    onClick = zoomIn
                )
                MapControlButton(
                    icon = R.drawable.ic_minus,
                    iconTint = R.color.map_control_button_gray,
                    onClick = zoomOut
                )
                MapControlButton(
                    icon = R.drawable.current_location,
                    iconTint = R.color.map_control_button_,
                    onClick = moveToCurrentLocation
                )
            }
        }
    }
}
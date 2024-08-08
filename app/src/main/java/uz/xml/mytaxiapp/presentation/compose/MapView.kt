package uz.xml.mytaxiapp.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import uz.xml.mytaxiapp.R

@Composable
fun MapView(
    modifier: Modifier,
    mapViewportState: MapViewportState,
    style: String = Style.TRAFFIC_DAY,
    currentLocation: Point,
) {
    MapboxMap(
        modifier = modifier,
        mapViewportState = mapViewportState,
        style = { MapStyle(style = style) },
    ) {
        ViewAnnotation(
            options = viewAnnotationOptions {
                geometry(currentLocation)
                allowOverlap(true)
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_car_pin),
                contentDescription = null
            )
        }
    }
}
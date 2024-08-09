package uz.xml.mytaxiapp.presentation.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.xml.mytaxiapp.R

@Composable
fun MapControlButtons(
    modifier: Modifier,
    moveToCurrentLocation: () -> Unit,
    zoomIn: () -> Unit,
    zoomOut: () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        MapControlButton(
            modifier = Modifier
                .border(4.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(14.dp)),
            icon = R.drawable.ic_chevrons,
            backgroundColor = MaterialTheme.colorScheme.background,
            onClick = { }
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MapControlButton(
                icon = R.drawable.ic_plus,
                onClick = zoomIn
            )
            MapControlButton(
                icon = R.drawable.ic_minus,
                onClick = zoomOut
            )
            MapControlButton(
                icon = R.drawable.current_location,
                iconTint = MaterialTheme.colorScheme.primary,
                onClick = moveToCurrentLocation
            )
        }
    }
}

@Composable
fun MapControlButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    iconTint: Color = MaterialTheme.colorScheme.onSecondary,
    backgroundColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = icon),
            tint = iconTint,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun CurrentLocationButton() {
    MapControlButton(icon = R.drawable.current_location, onClick = {})
}
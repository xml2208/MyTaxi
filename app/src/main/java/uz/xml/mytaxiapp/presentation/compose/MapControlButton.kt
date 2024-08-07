package uz.xml.mytaxiapp.presentation.compose

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.xml.mytaxiapp.R

@Composable
fun MapControlButton(
    @DrawableRes icon: Int,
    @ColorRes iconTint: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clickable(onClick = onClick)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(14.dp)
            )
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = icon),
            tint = colorResource(id = iconTint),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun CurrentLocationButton() {
    MapControlButton(icon = R.drawable.current_location, 0, {})
}
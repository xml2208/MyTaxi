package uz.xml.mytaxiapp.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.xml.mytaxiapp.R

@Composable
fun MapBottomSheet(modifier: Modifier) {
    Column(
        modifier = modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            Modifier
                .padding(6.dp)
                .size(width = 32.dp, height = 5.dp)
                .clip(RoundedCornerShape(6.dp))
                .padding(bottom = 6.dp),
            thickness = 5.dp
        )
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tariff),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.tarif),
                        modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "6/8",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                    )
                    Icon(
                        modifier = Modifier.padding(start = 9.dp),
                        painter = painterResource(id = R.drawable.ic_chevron),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_order),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.orders),
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "0",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                    )
                    Icon(
                        modifier = Modifier.padding(start = 9.dp),
                        painter = painterResource(id = R.drawable.ic_chevron),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rocket),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.bordur),
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomSheet() {
    MapBottomSheet(Modifier)
}
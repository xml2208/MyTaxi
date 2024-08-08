package uz.xml.mytaxiapp.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.xml.mytaxiapp.R

@Composable
fun MapTopBar(
    modifier: Modifier,
) {
    val selectedText = remember { mutableStateOf(0) }
    val text1Selected = selectedText.value == 0
    val text2Selected = selectedText.value == 1

    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MapControlButton(
            icon = R.drawable.ic_hamburger,
            iconTint = R.color.black, onClick = { }
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(14.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TopBarText(
                isSelected = text1Selected,
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.busy),
                onClick = { selectedText.value = 0 }
            )
            TopBarText(
                isSelected = text2Selected,
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.active),
                onClick = { selectedText.value = 1 }
            )
        }

        Text(
            text = "95",
            fontSize = 18.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier
                .background(colorResource(id = R.color.green), RoundedCornerShape(14.dp))
                .border(4.dp, Color.White, RoundedCornerShape(14.dp))
                .padding(18.dp)
        )
    }
}

@Composable
private fun TopBarText(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .background(
                color = colorResource(id = if (isSelected) R.color.green else R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 25.5.dp, vertical = (13).dp),
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontWeight = if (isSelected) FontWeight.Bold else null
    )
}
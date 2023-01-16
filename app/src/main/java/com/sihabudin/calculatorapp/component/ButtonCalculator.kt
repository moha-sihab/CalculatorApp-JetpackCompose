package com.sihabudin.calculatorapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sihabudin.calculatorapp.ui.theme.NetralVariant80

@Composable
fun ButtonCalculator(
    textButton: String,
    modifier: Modifier = Modifier,
    colorBackground: Color = Color.Black,
    colorText : Color = Color.White,
    sizeText : TextUnit = 32.sp,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(colorBackground)
            .border(width = 1.dp, color = NetralVariant80)
            .clickable {
                onClick()
            }
            .then(modifier)
    ) {
        Text(
            text = textButton,
            fontSize = sizeText,
            color = colorText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonCalculatorPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ButtonCalculator(
            textButton = "1", onClick = {}, modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "2", onClick = {}, modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "3", onClick = {}, modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

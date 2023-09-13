package com.app.compose_structure.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.app.compose_structure.theme.TextFiledTintColor
import com.app.compose_structure.theme.textStyleLabel

@Composable
fun DropDownText(
    modifier: Modifier = Modifier,
    headingText: String,
    value: String,
    textStyle: TextStyle = TextStyle.Default,
) {
    Column(modifier = modifier) {
        Text(text = headingText, style = textStyleLabel)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)) {
            Text(text = value, style = textStyle, modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = TextFiledTintColor)
        )
    }
}
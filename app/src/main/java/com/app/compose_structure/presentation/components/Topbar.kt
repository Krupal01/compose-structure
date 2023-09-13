package com.example.composedemo.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compose_structure.R
import com.app.compose_structure.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackPressed: () -> Unit = {},
    isBackButton: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Surface(shadowElevation = 3.dp) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        color = DarkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            },
            navigationIcon = {
                if (isBackButton) {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            "backIcon",
                            tint = DarkBlue
                        )
                    }
                }
            },
            actions = actions,

            )
    }
}


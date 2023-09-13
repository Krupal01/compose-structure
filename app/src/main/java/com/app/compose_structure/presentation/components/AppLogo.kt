package com.app.compose_structure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.compose_structure.R

@Composable
fun AppLogo(modifier: Modifier = Modifier, paddingValues: PaddingValues = PaddingValues()) {
    Column(Modifier.fillMaxWidth().padding(paddingValues).padding(start = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentScale = ContentScale.Inside, contentDescription = null, modifier = modifier)
    }
}
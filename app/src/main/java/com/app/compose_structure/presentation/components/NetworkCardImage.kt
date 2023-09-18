package com.app.compose_structure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NetworkImageCard(imageUrl: String, modifier: Modifier = Modifier) {

    val painter = rememberImagePainter(data = imageUrl,
        builder ={
            transformations(
//                GrayscaleTransformation(),       // Gray Scale Transformation
//                CircleCropTransformation()       // Circle Crop Transformation
            )
        })

    Box(
        modifier = modifier
            .size(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(360.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

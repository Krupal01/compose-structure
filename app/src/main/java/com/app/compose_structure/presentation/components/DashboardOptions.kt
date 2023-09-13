package com.example.composedemo.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compose_structure.presentation.viewmodel.DashboardOptions

@Preview
@Composable
fun DashboardOptionsGreed(
    onClick: (DashboardOptions) -> Unit = {},
    dashBoardMenu: List<DashboardOptions> = emptyList(),
) {

    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        items(dashBoardMenu) {
            DashboardOptionCard(dashBoardOption = it, onClick = onClick)
        }
    }
}

@Composable
fun DashboardOptionCard(
    onClick: (DashboardOptions) -> Unit = {},
    dashBoardOption: DashboardOptions
) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .aspectRatio(1.25f)
            .padding(3.dp)
            .alpha(if (dashBoardOption.isEnable) 1f else 0.2f)
            .paint(
                painterResource(id = dashBoardOption.bgImg),
                contentScale = ContentScale.FillBounds
            )
            .clickable(onClick = { if (dashBoardOption.isEnable) onClick(dashBoardOption) }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                painter = painterResource(id = dashBoardOption.iconImg),
                contentDescription = "",
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = dashBoardOption.labelName,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}



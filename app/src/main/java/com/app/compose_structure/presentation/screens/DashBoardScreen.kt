package com.app.compose_structure.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.composedemo.composable.TopBar

@Composable
fun DashBoardScreen() {

    Column {
        TopBar(title = "Dashboard")
        LazyColumn {

        }
    }
}
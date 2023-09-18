package com.app.compose_structure.presentation.screens

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose_structure.presentation.components.CustomDialog
import com.app.compose_structure.presentation.components.NetworkImageCard
import com.app.compose_structure.presentation.components.ProgressDialog
import com.app.compose_structure.presentation.viewmodel.DashboardViewModel
import com.example.composedemo.composable.TopBar

@Composable
fun DashBoardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        TopBar(title = "Dashboard" , actions = {
            IconButton(onClick = {
                viewModel.showDialog()
                viewModel.getUserList()
            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
            }
        })
        LazyColumn {
            items(uiState.userList){ item ->  
                Row (verticalAlignment = Alignment.CenterVertically){
                    Box{
                        NetworkImageCard(imageUrl = item?.avatar ?: "")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "${ item?.firstName } ${ item?.lastName }" , style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = item?.email ?: "")
                    }
                }
            }
        }
    }

    uiState.uiMessage?.let {
        CustomDialog(
            text = if (it.resMessage != null && it.params != null) stringResource(
                id = it.resMessage,
                *(it.params)
            ) else if (it.resMessage != null) stringResource(id = it.resMessage) else it.message
                ?: "",
            onConfirmClick = it.uiAction ?: viewModel::dismissErrorDialog,
            onDismissRequest = it.uiDismissAction ?: viewModel::dismissErrorDialog,
            shouldShowCancelButton = it.uiAction != null && it.uiDismissAction == null,
            onCancelClick = it.uiActionCancel ?: viewModel::dismissErrorDialog
        )
    }

    if (uiState.isLoading) {
        ProgressDialog {
            viewModel.changeProgressState()
        }
    }
}
package com.app.compose_structure.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose_structure.R
import com.app.compose_structure.presentation.components.DropDownText
import com.app.compose_structure.presentation.components.MultiChooseDialog
import com.app.compose_structure.presentation.components.ProgressDialog
import com.app.compose_structure.presentation.components.CustomDialog
import com.app.compose_structure.presentation.viewmodel.DashBoardUiState
import com.app.compose_structure.presentation.viewmodel.DashBoardViewModel
import com.app.compose_structure.presentation.viewmodel.DashboardOptionsLabel
import com.app.compose_structure.theme.DarkBlue
import com.app.compose_structure.theme.MainTheme
import com.example.composedemo.composable.DashboardOptionsGreed
import com.example.composedemo.composable.TopBar

@Composable
fun DashBoardScreenRoute(
    dashBoardViewModel: DashBoardViewModel = hiltViewModel(),
    onBackPress: () -> Unit,
    navigateToReceptionOptionScreen: () -> Unit,
    navigateToReceptionScreen: () -> Unit,
    navigateToPutAwayScreen: () -> Unit,
    navigateToExpeditionScreen: () -> Unit,
    navigateToShippingScreen: () -> Unit,
    navigateToReturnScreen: () -> Unit,
    navigateToLocationTransferScreen: () -> Unit,
    navigateToInventoryScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {

    val uiState by dashBoardViewModel.uiState.collectAsState()

    DashBoardScreen(
        uiState = uiState,
        navigateToReceptionScreen = dashBoardViewModel::onReceptionClick,
        navigateToPutAwayScreen = navigateToPutAwayScreen,
        navigateToExpeditionScreen = navigateToExpeditionScreen,
        navigateToShippingScreen = navigateToShippingScreen,
        navigateToReturnScreen = navigateToReturnScreen,
        navigateToLocationTransferScreen = navigateToLocationTransferScreen,
        navigateToInventoryScreen = navigateToInventoryScreen,
        navigateToLoginScreen = navigateToLoginScreen,
        onShowDialog = dashBoardViewModel::changeSelectOwnerDialogState,
        onLogout = dashBoardViewModel::onLogOut
    )

    if (uiState.shouldShowDialog) {
        MultiChooseDialog(
            title = stringResource(id = R.string.select_owner),
            searchHitText = stringResource(id = R.string.search_owner),
            list = uiState.ownerNameList,
            selectText = uiState.ownerName,
            onDone = {
                dashBoardViewModel.ownerChange(it)
            },
            onCancel = {
                dashBoardViewModel.changeSelectOwnerDialogState(false)
            }
        )
    }

    LaunchedEffect(key1 = uiState.isUseEAN128, block = {
        uiState.isUseEAN128?.let {
            if (it) {
                navigateToReceptionOptionScreen()
            } else {
                navigateToReceptionScreen()
            }
        }
    })


    uiState.uiResMessage?.let {
        CustomDialog(
            text = stringResource(id = it),
            onConfirmClick = dashBoardViewModel::dismissErrorDialog,
            onDismissRequest = dashBoardViewModel::dismissErrorDialog
        )
    }

    uiState.uiMessage?.let {
        CustomDialog(
            text = it,
            onConfirmClick = dashBoardViewModel::dismissErrorDialog,
            onDismissRequest = dashBoardViewModel::dismissErrorDialog
        )
    }

    if (uiState.isLoading) {
        ProgressDialog {
            dashBoardViewModel.dismissProgressDialog()
        }
    }
}

@Composable
fun DashBoardScreen(
    uiState: DashBoardUiState,
    navigateToReceptionScreen: () -> Unit,
    navigateToPutAwayScreen: () -> Unit,
    navigateToExpeditionScreen: () -> Unit,
    navigateToShippingScreen: () -> Unit,
    navigateToReturnScreen: () -> Unit,
    navigateToLocationTransferScreen: () -> Unit,
    navigateToInventoryScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    onShowDialog: (Boolean) -> Unit,
    onLogout: () -> Unit
) {

    LaunchedEffect(key1 = uiState.isLogout, block = {
        if (uiState.isLogout) {
            // Redirect Login Screen
            navigateToLoginScreen()
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(title = stringResource(R.string.dashboard), actions = {
            IconButton(onClick = onLogout) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    modifier = Modifier.size(22.dp),
                    contentDescription = stringResource(R.string.logout),
                    tint = DarkBlue
                )
            }
        })

        DropDownText(
            headingText = "",
            value = uiState.ownerName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .clickable { onShowDialog(true) },
            textStyle = MaterialTheme.typography.labelLarge,
        )

        Spacer(modifier = Modifier.size(25.dp))
        DashboardOptionsGreed(onClick = { dashboardOption ->
            when (dashboardOption.labelName) {
                DashboardOptionsLabel.RECEPTION.labelName -> navigateToReceptionScreen()
                DashboardOptionsLabel.PUT_AWAY.labelName -> navigateToPutAwayScreen()
                DashboardOptionsLabel.EXPEDITION.labelName -> navigateToExpeditionScreen()
                DashboardOptionsLabel.SHIPPING.labelName -> navigateToShippingScreen()
                DashboardOptionsLabel.RETURN.labelName -> navigateToReturnScreen()
                DashboardOptionsLabel.LOCATION_TRANSFER.labelName -> navigateToLocationTransferScreen()
                DashboardOptionsLabel.INVENTORY.labelName -> navigateToInventoryScreen()
            }
        }, dashBoardMenu = uiState.dashboardMenuList)
    }
}

@Preview(showBackground = true)
@Composable
fun DashBoardPreview() {
    MainTheme {
        DashBoardScreen(
            DashBoardUiState(),
            navigateToReceptionScreen = {},
            navigateToPutAwayScreen = {},
            navigateToExpeditionScreen = {},
            navigateToShippingScreen = {},
            navigateToReturnScreen = {},
            navigateToLoginScreen = {},
            navigateToLocationTransferScreen = {},
            navigateToInventoryScreen = {},
            onShowDialog = {}
        ) {

        }
    }
}
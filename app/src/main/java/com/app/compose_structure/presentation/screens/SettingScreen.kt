package com.app.compose_structure.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose_structure.R
import com.app.compose_structure.presentation.components.HeadingTextFiled
import com.app.compose_structure.presentation.components.AppLogo
import com.app.compose_structure.presentation.viewmodel.SettingUIState
import com.app.compose_structure.presentation.viewmodel.SettingViewModel
import com.app.compose_structure.theme.DarkBlue
import com.app.compose_structure.theme.GreenColor
import com.app.compose_structure.theme.MainTheme

@Composable
fun SettingScreenRoute(
    viewModel: SettingViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.isSaved, block = {
        if(uiState.isSaved) {
            onBackPress()
        }
    })

    SettingScreen(
        uiState = uiState,
        onBackPress = onBackPress,
        onUsernameChange = viewModel::usernameChange,
        onWebServiceUrlChange = viewModel::webServiceUrl,
        onOwnerChange = viewModel::ownerChange,
        onChangeMultiScanig = viewModel::changeMultiScaning,
        onChangeTestingKey = viewModel::changeTestingKey,
        onSave = viewModel::save
    )

    uiState.uiResMessage?.let {
        AlertDialog(onDismissRequest = viewModel::dismissErrorDialog,
            title = {
                Text(text = stringResource(id = R.string.app_name))
            }, text = {
                Text(text = stringResource(id = it))
            },
            confirmButton = {
                TextButton(onClick = viewModel::dismissErrorDialog) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}

@Composable
fun SettingScreen(
    uiState: SettingUIState,
    onBackPress: () -> Unit,
    onUsernameChange: (String) -> Unit,
    onOwnerChange: (String) -> Unit,
    onWebServiceUrlChange: (String) -> Unit,
    onChangeMultiScanig: (Boolean) -> Unit,
    onChangeTestingKey: (Boolean) -> Unit,
    onSave: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)) {
        Spacer(modifier = Modifier.size(10.dp))

        IconButton(onClick = onBackPress, modifier = Modifier.padding(start = 2.dp)) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = DarkBlue
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_key),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(6.dp)
            )

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = stringResource(id = R.string.setting),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )

            Column(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.Center) {
                HeadingTextFiled(
                    headingText = stringResource(id = R.string.user),
                    value = uiState.username,
                    onValueChange = onUsernameChange,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    textStyle = MaterialTheme.typography.labelLarge,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(25.dp))

                HeadingTextFiled(
                    headingText = stringResource(id = R.string.owner),
                    value = uiState.owner,
                    onValueChange = onOwnerChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(25.dp))

                HeadingTextFiled(
                    headingText = stringResource(id = R.string.web_service_url),
                    value = uiState.webServiceUrl,
                    onValueChange = onWebServiceUrlChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(20.dp))

                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = uiState.isMultiScanning,
                        onCheckedChange = onChangeMultiScanig,
                        colors = CheckboxDefaults.colors(checkedColor = DarkBlue)
                    )

                    Text(text = stringResource(id = R.string.multi_scanning))
                }

                Spacer(modifier = Modifier.size(8.dp))

                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = uiState.isScandiTestingKey,
                        onCheckedChange = onChangeTestingKey,
                        colors = CheckboxDefaults.colors(checkedColor = DarkBlue)
                    )

                    Text(text = stringResource(id = R.string.scandi_testing_key))
                }

                Spacer(modifier = Modifier.size(30.dp))

                Button(
                    onClick = onSave,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenColor)
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            AppLogo(
                modifier = Modifier.width(180.dp),
                paddingValues = PaddingValues(vertical = 20.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    MainTheme {
        SettingScreen(SettingUIState(), {}, {}, {}, {}, {}, {}, {})
    }
}
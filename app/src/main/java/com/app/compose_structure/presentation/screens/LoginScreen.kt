package com.app.compose_structure.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose_structure.R
import com.app.compose_structure.presentation.components.DropDownText
import com.app.compose_structure.presentation.components.HeadingTextFiled
import com.app.compose_structure.presentation.components.AppLogo
import com.app.compose_structure.presentation.components.MultiChooseDialog
import com.app.compose_structure.presentation.components.ProgressDialog
import com.app.compose_structure.presentation.components.CustomDialog
import com.app.compose_structure.presentation.viewmodel.LoginUIState
import com.app.compose_structure.presentation.viewmodel.LoginViewModel
import com.app.compose_structure.theme.DarkBlue
import com.app.compose_structure.theme.MainTheme

@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onBackPress: () -> Unit,
    navigateToSettingScreen: () -> Unit,
    navigateToDashboardScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.isLoginSuccess, block = {
        if (uiState.isLoginSuccess) {
            // Redirect Dashboard Screen
            navigateToDashboardScreen()
        }
    })

    LoginScreen(
        uiState = uiState,
        navigateToSettingScreen = navigateToSettingScreen,
        onUsernameChange = viewModel::usernameChange,
        onPasswordChange = viewModel::passwordChange,
        onShowDialog = viewModel::changeSelectOwnerDialogState,
        onUserNameEnter = viewModel::fetchUserOwnerList,
        onLogin = viewModel::loginUser
    )

    if (uiState.shouldShowDialog) {
        MultiChooseDialog(
            title = stringResource(id = R.string.select_owner),
            searchHitText = stringResource(id = R.string.search_owner),
            list = uiState.ownerNameList,
            selectText = uiState.owner,
            onDone = {
                viewModel.ownerChange(it)
            },
            onCancel = {
                viewModel.changeSelectOwnerDialogState(false)
            }
        )
    }

    uiState.uiResMessage?.let {
        CustomDialog(
            text = stringResource(id = it),
            onConfirmClick = viewModel::dismissErrorDialog,
            onDismissRequest = viewModel::dismissErrorDialog
        )
    }

    uiState.uiMessage?.let {
        CustomDialog(
            text = it,
            onConfirmClick = viewModel::dismissErrorDialog,
            onDismissRequest = viewModel::dismissErrorDialog
        )
    }

    if (uiState.isLoading) {
        ProgressDialog {
            viewModel.dismissProgressDialog()
        }
    }
}

@Composable
fun LoginScreen(
    uiState: LoginUIState,
    navigateToSettingScreen: () -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onShowDialog: (Boolean) -> Unit,
    onUserNameEnter: () -> Unit,
    onLogin: () -> Unit,
) {

    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(68.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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

            IconButton(onClick = navigateToSettingScreen, modifier = Modifier.offset(x = 15.dp)) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = DarkBlue)
            }
        }

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = stringResource(id = R.string.welcome_back),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(id = R.string.login_to_continue),
            style = MaterialTheme.typography.titleLarge
        )

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            HeadingTextFiled(
                headingText = stringResource(id = R.string.username),
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
                    .onFocusChanged {
                        if (uiState.username.isNotEmpty() && !it.isFocused) {
                            Log.d("TAG", "LoginScreen: ${it}")
                            onUserNameEnter()
                        }
                    }
            )

            Spacer(modifier = Modifier.size(25.dp))

            HeadingTextFiled(
                headingText = stringResource(id = R.string.password),
                value = uiState.password,
                onValueChange = onPasswordChange,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                textStyle = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(25.dp))

            DropDownText(
                headingText = stringResource(id = R.string.select_owner),
                value = uiState.owner,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onShowDialog(true) },
                textStyle = MaterialTheme.typography.labelLarge,
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MainTheme {
        LoginScreen(LoginUIState(), {},{}, {}, {}, {}, {})
    }
}
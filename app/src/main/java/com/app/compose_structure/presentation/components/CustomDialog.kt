package com.app.compose_structure.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compose_structure.R
import com.app.compose_structure.theme.GreenColor

@Composable
fun CustomDialog(
    text: String,
    title: String = stringResource(id = R.string.app_name),
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
    confirmBtnText: String = stringResource(id = R.string.ok),
    cancelBtnText: String = stringResource(id = R.string.cancel),
    onCancelClick: () -> Unit = {},
    shouldShowCancelButton: Boolean = false
) {
    val context = LocalContext.current
    /*val soundManger = remember {
        SoundManager(context)
    }
    LaunchedEffect(key1 = Unit, block = {
        if(!shouldShowCancelButton) {
            soundManger.playSoundAndVibrate()
        }
    })*/
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        }, text = {
            Text(text = text)
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = confirmBtnText)
            }
        },
        dismissButton = if (shouldShowCancelButton) {
            {
                TextButton(onClick = onCancelClick) {
                    Text(text = cancelBtnText)
                }
            }
        } else null
    )

}

@Preview
@Composable
fun CustomEditDialog(
    title: String = stringResource(id = R.string.app_name),
    headingText: String = "",
    onConfirmClick: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    confirmBtnText: String = stringResource(id = R.string.ok),
    cancelBtnText: String = stringResource(id = R.string.cancel),
    onCancelClick: () -> Unit = {},
    shouldShowCancelButton: Boolean = false,
    initValue: String = ""
) {

    val text = remember {
        mutableStateOf(initValue)
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        },
        text = {
            HeadingTextFiled(
                headingText = headingText,
                value = text.value,
                onValueChange = { text.value = it },
                textStyle = MaterialTheme.typography.labelLarge,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirmClick(text.value) },
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GreenColor)
            ) {
                Text(
                    text = stringResource(id = R.string.ok),
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    )

}
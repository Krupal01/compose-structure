package com.app.compose_structure.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose_structure.theme.MainTheme
import com.app.compose_structure.theme.TextFiledTintColor
import com.app.compose_structure.theme.textStyleLabel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadingTextFiled(
    headingText: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    contentPadding: PaddingValues = PaddingValues(top = 6.dp,bottom = 6.dp),
    textStyle: TextStyle = TextStyle.Default,
    shape: Shape = TextFieldDefaults.outlinedShape,
    color: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        unfocusedIndicatorColor = TextFiledTintColor,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Column {
        Text(text = headingText, style = textStyleLabel)

        BasicTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            readOnly = readOnly,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            maxLines = maxLines,
            minLines = minLines,
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = it,
                singleLine = singleLine,
                enabled = enabled,
                shape = shape,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                colors = color,
                contentPadding = contentPadding,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeadingTextFiledPreview() {
    MainTheme {
        HeadingTextFiled(
            "Username",
            "Text",
            {}
        )
    }
}
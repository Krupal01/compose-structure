package com.app.compose_structure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.compose_structure.R
import com.app.compose_structure.theme.DarkBlue
import com.app.compose_structure.theme.GrayColor
import com.app.compose_structure.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChooseDialog (
    title: String,
    searchHitText: String,
    list: List<String>,
    selectText: String = "",
    onDone: (String) -> Unit,
    onCancel: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    var selectItem by remember(selectText) { mutableStateOf(selectText) }

    val filterList = remember(searchText, list) {
        list.filter { it.contains(searchText, true) }
    }

    val interactionSource = remember { MutableInteractionSource() }

    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = DarkBlue)
                        .padding(12.dp),
                    color = Color.White
                )

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleMedium,
                ) {
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = searchText,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = GrayColor,
                            unfocusedIndicatorColor = GrayColor,
                            focusedIndicatorColor = GrayColor
                        ),
                        placeholder = {
                            Text(text = searchHitText)
                        },
                        contentPadding = PaddingValues(horizontal = 6.dp, 8.dp)
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(filterList) {
                        CustomListItem(item = it, isSelected = selectItem == it, onClick = {
                            selectItem = it
                        })
                    }
                }

                Divider()
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(
                        onClick = onCancel,
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }

                    TextButton(
                        onClick = {
                            onDone(selectItem)
                        }, enabled = selectItem.isNotEmpty()
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    }

}

@Composable
private fun CustomListItem(item: String, isSelected: Boolean, onClick: (String) -> Unit) {
    val matrix = ColorMatrix()
    matrix.setToScale(1f / 255f, 167 / 255f, 227 / 255f, 1f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        )
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.lighting(
                    Color.White,
                    MaterialTheme.colorScheme.primary
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiChooseDialogPreview() {
    MainTheme {
        Column(Modifier.fillMaxSize()) {
            MultiChooseDialog(
                title = "Select Owner",
                searchHitText = "Search Owner",
                list = listOf("ABC", "AB1"),
                onDone = {},
                onCancel = {})
        }
    }
}
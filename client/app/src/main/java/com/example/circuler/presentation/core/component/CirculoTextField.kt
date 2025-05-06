package com.example.circuler.presentation.core.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoTextField(
    paddingValues: PaddingValues,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    textAlign: TextAlign = TextAlign.Start,
    textFieldValue: String = "",
    placeHolder: String = "",
    singleLine: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    var textFieldState by remember { mutableStateOf(TextFieldValue(textFieldValue)) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BackHandler {
        focusManager.clearFocus()
    }

    BasicTextField(
        value = textFieldState,
        onValueChange = { newValue ->
            textFieldState = newValue
            onValueChange(newValue.text)
        },
        textStyle = CirculerTheme.typography.title1R16.copy(
            color = CirculerTheme.colors.grayScale12,
            textAlign = textAlign,
        ),
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .roundedBackgroundWithBorder(
                cornerRadius = 8.dp,
                backgroundColor = CirculerTheme.colors.grayScale2,
                borderColor = when {
                    isFocused -> CirculerTheme.colors.green1
                    else -> CirculerTheme.colors.grayScale5
                },
                borderWidth = 1.dp
            )
            .padding(paddingValues),
        cursorBrush = SolidColor(CirculerTheme.colors.green1),
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = if (textAlign == TextAlign.End) Alignment.CenterEnd else Alignment.TopStart
                ) {
                    innerTextField()

                    if (textFieldState.text.isEmpty()) {
                        Text(
                            text = placeHolder,
                            color = CirculerTheme.colors.grayScale7,
                            style = CirculerTheme.typography.title1R16,
                            textAlign = textAlign,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                if (content != {}) {
                    Spacer(modifier = Modifier.width(2.dp))

                    content()
                }
            }
        }
    )
}

@Preview
@Composable
private fun CirculoTextFieldPreview() {
    CirculerTheme {
        CirculoTextField(
            paddingValues = PaddingValues(16.dp),
            onValueChange = {}
        )
    }
}

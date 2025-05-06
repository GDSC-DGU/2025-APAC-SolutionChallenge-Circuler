package com.example.circuler.presentation.core.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.ui.theme.CirculerTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CirculoBottomSheet(
    isOpenBottomSheet: Boolean,
    title: String,
    content: @Composable () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    if (isOpenBottomSheet) {
        coroutineScope.launch {
            sheetState.show()
        }

        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
            containerColor = CirculerTheme.colors.grayScale1,
            contentColor = CirculerTheme.colors.green1,
            dragHandle = null
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = title,
                        style = CirculerTheme.typography.title1R16.copy(
                            color = CirculerTheme.colors.grayScale12
                        ),
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    Image(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "close",
                        modifier = Modifier.noRippleClickable {
                            onDismissRequest()
                        }
                    )
                }
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun CirculoBottomSheetPreview() {
    CirculerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 5.dp)
        ) {
            val sheetState = rememberModalBottomSheetState()
            var showBottomSheet by remember { mutableStateOf(false) }

            Text(
                text = "click",
                modifier = Modifier.noRippleClickable {
                    showBottomSheet = true
                }
            )

            CirculoBottomSheet(
                isOpenBottomSheet = showBottomSheet,
                sheetState = sheetState,
                title = "Packaging Type",
                content = {
                    PackagingTypeContent()
                },
                onDismissRequest = {
                    showBottomSheet = false
                }
            )
        }
    }
}
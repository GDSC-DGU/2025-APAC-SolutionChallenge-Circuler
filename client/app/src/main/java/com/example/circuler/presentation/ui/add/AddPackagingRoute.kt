package com.example.circuler.presentation.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.presentation.core.component.CirculoBottomSheet
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.component.CirculoTextField
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.component.PackagingTypeContent
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.type.PackagingType
import com.example.circuler.presentation.ui.add.component.AddSubTitle
import com.example.circuler.presentation.ui.add.component.AddTitle
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun AddPackagingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: AddPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is AddPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    AddPackagingSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    AddPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToHome = viewModel::navigateToHome,
        state = state,
        onTypeChanged = viewModel::updatedType,
        onLocationChanged = viewModel::updatedLocation,
        onQuantityChanged = viewModel::updatedQuantity,
        isOpenBottomSheet = state.isOpenBottomSheet,
        selectedIndex = state.selectedIndex,
        updateSelectedIndex = viewModel::updateSelectedIndex,
        openBottomSheet = viewModel::controlBottomSheet,
        onDismissBottomSheetRequest = viewModel::controlBottomSheet,
        onButtonClick = viewModel::postPackageRequest
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    state: AddPackagingState,
    onTypeChanged: (String) -> Unit,
    onLocationChanged: (String) -> Unit,
    onQuantityChanged: (String) -> Unit,
    isOpenBottomSheet: Boolean,
    selectedIndex: Int,
    updateSelectedIndex: (Int) -> Unit,
    openBottomSheet: () -> Unit,
    onDismissBottomSheetRequest: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    // todo:
    val options = PackagingType.entries.toTypedArray()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CirculoTopBar(
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .noRippleClickable { navigateUp() }
                        .padding(all = 10.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                    contentDescription = "back"
                )
            }
        )

        AddTitle()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp, alignment = Alignment.CenterVertically)
        ) {
            AddSubTitle(
                text = "Packaging type"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp * 0.060).dp)
                    .roundedBackgroundWithBorder(
                        cornerRadius = 8.dp,
                        backgroundColor = CirculerTheme.colors.grayScale2,
                        borderColor = CirculerTheme.colors.grayScale5,
                        borderWidth = 1.dp
                    )
                    .noRippleClickable {
                        openBottomSheet()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 10.dp),
                    text = options[selectedIndex].text,
                    style = CirculerTheme.typography.title1R16.copy(
                        color = CirculerTheme.colors.grayScale12
                    )
                )

                Icon(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .size(24.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                    contentDescription = null
                )
            }

            AddSubTitle(
                text = "Quantity"
            )
            CirculoTextField(
                paddingValues = PaddingValues(16.dp),
                textFieldValue = state.uiState.quantity,
                onValueChange = onQuantityChanged,
                keyboardType = KeyboardType.Number,
                placeHolder = "Please enter a quantity from 1 to 10"
            )

            AddSubTitle(
                text = "Shop Location"
            )
            CirculoTextField(
                paddingValues = PaddingValues(16.dp),
                textFieldValue = state.uiState.location,
                onValueChange = onLocationChanged
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            CirculoButton(
                text = "submit",
                onClick = onButtonClick
            )
        }
    }

    CirculoBottomSheet(
        isOpenBottomSheet = isOpenBottomSheet,
        title = "Packaging Type",
        content = {
            PackagingTypeContent(
                activeIndex = selectedIndex,
                onClick = { index ->
                    updateSelectedIndex(index)
                }
            )
        },
        onDismissRequest = {
            onTypeChanged(options[selectedIndex].toString())
            onDismissBottomSheetRequest()
        }
    )
}

@Preview
@Composable
fun AddPackagingScreenPreview() {
    CirculerTheme {
        AddPackagingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToHome = {},
            state = AddPackagingState(),
            onTypeChanged = {},
            onLocationChanged = {},
            onQuantityChanged = {},
            isOpenBottomSheet = false,
            selectedIndex = 0,
            updateSelectedIndex = {},
            openBottomSheet = { },
            onDismissBottomSheetRequest = { },
            onButtonClick = {}
        )
    }
}

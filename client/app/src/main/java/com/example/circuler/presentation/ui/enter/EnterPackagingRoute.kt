package com.example.circuler.presentation.ui.enter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.presentation.core.component.CirculoBottomSheet
import com.example.circuler.presentation.core.component.CirculoBottomSheetButton
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.component.CirculoTextField
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.component.DeliveryTypeContent
import com.example.circuler.presentation.core.component.PackagingTypeContent
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.type.DeliveryType
import com.example.circuler.presentation.type.PackagingType
import com.example.circuler.presentation.ui.add.component.AddSubTitle
import com.example.circuler.presentation.ui.add.component.AddTitle
import com.example.circuler.ui.theme.CirculerTheme
import timber.log.Timber

@Composable
fun EnterPackagingRoute(
    paddingValues: PaddingValues,
    requestId: Int,
    navigateUp: () -> Unit,
    navigateToConfirmPackage: () -> Unit,
    viewModel: EnterPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is EnterPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    EnterPackagingSideEffect.NavigateToConfirmPackage -> navigateToConfirmPackage()
                }
            }
    }

    EnterPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToConfirmPackage = viewModel::navigateToConfirmPackage,
        state = state,
        onTypeChanged = viewModel::updatedType,
        onMethodChanged = viewModel::updatedMethod,
        onLocationChanged = viewModel::updatedLocation,
        isOpenPackageBottomSheet = state.isOpenPackageBottomSheet,
        selectedPackageIndex = state.selectedPackageIndex,
        updateSelectedPackageIndex = viewModel::updatePackageSelectedIndex,
        openPackageBottomSheet = viewModel::controlPackageBottomSheet,
        onDismissPackageBottomSheetRequest = viewModel::controlPackageBottomSheet,
        isOpenDeliveryBottomSheet = state.isOpenDeliveryBottomSheet,
        selectedDeliveryIndex = state.selectedDeliveryIndex,
        updateSelectedDeliveryIndex = viewModel::updateDeliverySelectedIndex,
        openDeliveryBottomSheet = viewModel::controlDeliveryBottomSheet,
        onDismissDeliveryBottomSheetRequest = viewModel::controlDeliveryBottomSheet,
        onButtonClick = {
            viewModel.postPackagingRequest(requestId = requestId)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToConfirmPackage: () -> Unit,
    state: EnterPackagingState,
    onTypeChanged: (String) -> Unit,
    onMethodChanged: (String) -> Unit,
    onLocationChanged: (String) -> Unit,
    isOpenPackageBottomSheet: Boolean,
    selectedPackageIndex: Int,
    updateSelectedPackageIndex: (Int) -> Unit,
    openPackageBottomSheet: () -> Unit,
    onDismissPackageBottomSheetRequest: () -> Unit,
    isOpenDeliveryBottomSheet: Boolean,
    selectedDeliveryIndex: Int,
    updateSelectedDeliveryIndex: (Int) -> Unit,
    openDeliveryBottomSheet: () -> Unit,
    onDismissDeliveryBottomSheetRequest: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val typeOptions = PackagingType.entries.toTypedArray()
    val methodOptions = DeliveryType.entries.toTypedArray()

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
            CirculoBottomSheetButton(
                label = typeOptions[selectedPackageIndex].text,
                onClick = {
                    openPackageBottomSheet()
                }
            )

            AddSubTitle(
                text = "Delivery Method"
            )
            CirculoBottomSheetButton(
                label = methodOptions[selectedDeliveryIndex].text,
                onClick = {
                    openDeliveryBottomSheet()
                }
            )

            AddSubTitle(
                text = "User Location"
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
        isOpenBottomSheet = isOpenPackageBottomSheet,
        title = "Packaging Type",
        content = {
            PackagingTypeContent(
                activeIndex = selectedPackageIndex,
                onClick = { index ->
                    updateSelectedPackageIndex(index)
                }
            )
        },
        onDismissRequest = {
            Timber.d(typeOptions[selectedPackageIndex].toString())
            onTypeChanged(typeOptions[selectedPackageIndex].toString())
            onDismissPackageBottomSheetRequest()
        }
    )

    CirculoBottomSheet(
        isOpenBottomSheet = isOpenDeliveryBottomSheet,
        title = "Delivery Type",
        content = {
            DeliveryTypeContent(
                activeIndex = selectedDeliveryIndex,
                onClick = { index ->
                    updateSelectedDeliveryIndex(index)
                }
            )
        },
        onDismissRequest = {
            Timber.d(methodOptions[selectedDeliveryIndex].toString())
            onMethodChanged(methodOptions[selectedDeliveryIndex].toString())
            onDismissDeliveryBottomSheetRequest()
        }
    )
}

@Preview
@Composable
fun EnterPackagingScreenPreview() {
    CirculerTheme {
        EnterPackagingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToConfirmPackage = {},
            state = EnterPackagingState(),
            onTypeChanged = {},
            onMethodChanged = {},
            onLocationChanged = {},
            isOpenPackageBottomSheet = false,
            selectedPackageIndex = 0,
            updateSelectedPackageIndex = {},
            openPackageBottomSheet = { },
            onDismissPackageBottomSheetRequest = { },
            isOpenDeliveryBottomSheet = false,
            selectedDeliveryIndex = 0,
            updateSelectedDeliveryIndex = { },
            openDeliveryBottomSheet = { },
            onDismissDeliveryBottomSheetRequest = { },
            onButtonClick = {}
        )
    }
}

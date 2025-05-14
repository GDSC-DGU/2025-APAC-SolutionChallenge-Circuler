package com.example.circuler.presentation.ui.upload

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.domain.entity.PackageImageEntity
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.ImageUiState
import com.example.circuler.presentation.ui.upload.camerax.CameraXFactory
import com.example.circuler.ui.theme.CirculerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun UploadPackagingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: UploadPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is UploadPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    UploadPackagingSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    UploadPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToHome = viewModel::navigateToHome,
        submitImage = viewModel::postPackageImage,
        updatePermissionGranted = viewModel::updatePermissionGranted,
        updatePermissionNotGranted = viewModel::updatePermissionNotGranted,
        state = state.uiState
    )
}

@Composable
fun UploadPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: ImageUiState<PackageImageEntity>,
    submitImage: (Int, Uri) -> Unit,
    updatePermissionGranted: () -> Unit,
    updatePermissionNotGranted: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (state) {
            ImageUiState.Failure -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CirculerTheme.colors.grayScale1)
                        .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Image upload failed..",
                            style = CirculerTheme.typography.heading1B20
                        )
                    }

                    CirculoButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                        text = "back to home",
                        onClick = {
                            navigateToHome()
                        }
                    )
                }
            }

            ImageUiState.Idle -> {
                CameraScreen(
                    submitImage = submitImage
                )
            }

            ImageUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .height((LocalConfiguration.current.screenHeightDp).dp)
                ) {
                    CirculoLoadingView()
                }
            }

            is ImageUiState.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CirculerTheme.colors.grayScale1)
                        .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_upload_success),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    CirculoButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                        text = "accept",
                        onClick = {
                            navigateToHome()
                        }
                    )
                }
            }

            ImageUiState.PermissionNotGranted -> {
                RequestPermission(
                    updatePermissionGranted = updatePermissionGranted,
                    updatePermissionNotGranted = updatePermissionNotGranted
                )
            }
        }
    }
}

@Composable
private fun CameraScreen(
    submitImage: (Int, Uri) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraScope = rememberCoroutineScope()
    val context = LocalContext.current
    val cameraX = remember { CameraXFactory.create() }
    val previewView = remember { mutableStateOf<PreviewView?>(null) }
    val facing = cameraX.getFacingState().collectAsState()

    LaunchedEffect(Unit) {
        cameraX.initialize(context = context)
        previewView.value = cameraX.getPreviewView()
    }

    DisposableEffect(facing.value) {
        cameraScope.launch(Dispatchers.Main) {
            cameraX.startCamera(lifecycleOwner = lifecycleOwner)
        }
        onDispose {
            cameraX.unBindCamera()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        previewView.value?.let { preview -> AndroidView(modifier = Modifier.fillMaxSize(), factory = { preview }) {} }

        CirculoButton(
            modifier = Modifier
                .padding(20.dp),
            text = "take a picture",
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val uri = cameraX.takePicture()
                    uri?.let {
                        Timber.d("CameraX Photo Save: $uri")

                        submitImage(1, uri)
                    }
                }
            }
        )
    }
}

@Composable
private fun RequestPermission(
    updatePermissionGranted: () -> Unit,
    updatePermissionNotGranted: () -> Unit,

    ) {
    val context = LocalContext.current

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                updatePermissionGranted()
            } else {
                updatePermissionNotGranted()
            }
        }

    if (context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        LaunchedEffect(Unit) {
            cameraLauncher.launch(Manifest.permission.CAMERA)
        }
    } else {
        updatePermissionGranted()
    }
}

@Preview
@Composable
fun UploadPackagingScreenPreview() {
    CirculerTheme {
//        UploadPackagingScreen(
//            paddingValues = PaddingValues(),
//            navigateUp = {},
//            navigateToHome = {},
//            submitImage = ,
//            updatePermissionGranted = {},
//            updatePermissionNotGranted = {},
//            state = ImageUiState.Failure
//        )
    }
}
